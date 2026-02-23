package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base.impl;

import ai.neuron.copilot.knowledge.foundation.blob.BlobInputStreamDTO;
import ai.neuron.copilot.knowledge.foundation.blob.BlobObjectKey;
import ai.neuron.copilot.knowledge.foundation.blob.ObjectStorageClient;
import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceAlreadyExistException;
import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.foundation.core.io.FoundationIOUtils;
import ai.neuron.copilot.knowledge.foundation.core.json.FoundationJsonCodec;
import ai.neuron.copilot.knowledge.rag.app.port.out.config.DatasetMetadata;
import ai.neuron.copilot.knowledge.rag.app.port.out.context.CurrentOperatorProvider;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.DifyDatasetsClient;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.CreateDocumentByFileRequestData;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.UpdateDocumentMetadataRequest;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.response.CreateDocumentByFileResponse;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DifyDocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DifyKnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.SysTenantDifyRegisterRepository;
import ai.neuron.copilot.knowledge.rag.app.service.knowledge_base.KnowledgeBaseImplementer;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;
import ai.neuron.copilot.knowledge.rag.domain.sys.model.SysTenantDifyRegister;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DifyKnowledgeBaseImplementer implements KnowledgeBaseImplementer {

    private final DifyKnowledgeBaseRepository difyKnowledgeBaseRepository;

    private final DifyDocumentRepository difyDocumentRepository;

    private final ObjectStorageClient objectStorageClient;

    private final FoundationJsonCodec foundationJsonCodec;

    private final DifyDatasetsClient difyDatasetsClient;

    private final CurrentOperatorProvider currentOperatorProvider;

    private final SysTenantDifyRegisterRepository sysTenantDifyRegisterRepository;

    @Transactional
    @Override
    public void create(KnowledgeBase knowledgeBase) {
        SysTenantDifyRegister sysTenantDifyRegister = sysTenantDifyRegisterRepository
                .fetchByTenantId(currentOperatorProvider.tenantId())
                .orElseThrow(ResourceNotFoundException::new);
        DifyKnowledgeBase difyKnowledgeBase = DifyKnowledgeBase.create(knowledgeBase.getId(),
                DifyDatasetId.reconstitute(sysTenantDifyRegister.datasetId()));
        boolean saved = difyKnowledgeBaseRepository.save(difyKnowledgeBase);
        if (!saved) {
            throw new SystemException();
        }
    }

    @Transactional
    @Override
    public void delete(KnowledgeBase knowledgeBase) {
        boolean deleted = difyKnowledgeBaseRepository.delete(knowledgeBase.getId());
        if (!deleted) {
            throw new ResourceNotFoundException();
        }
    }

    @Transactional
    @Override
    public void createDocument(KnowledgeBase knowledgeBase, Document document) {

        SysTenantDifyRegister sysTenantDifyRegister = sysTenantDifyRegisterRepository
                .fetchByTenantId(currentOperatorProvider.tenantId())
                .orElseThrow(ResourceNotFoundException::new);

        BlobObjectKey blobObjectKey = BlobObjectKey.reconstitute(document.getObjectKey());
        BlobInputStreamDTO blobInputStreamDTO = objectStorageClient.fetch(blobObjectKey);
        String data = foundationJsonCodec.encode(CreateDocumentByFileRequestData.defaultRequest());
        Resource resource = FoundationIOUtils.toResource(blobInputStreamDTO.getInputStream(),
                document.getOriginalFileName(), blobInputStreamDTO.getSize());

        CreateDocumentByFileResponse response = difyDatasetsClient.createDocumentByFile(
                sysTenantDifyRegister.datasetId(), data, resource);
        String difyDocumentId = Optional.ofNullable(response).map(CreateDocumentByFileResponse::getDocument)
                .map(CreateDocumentByFileResponse.Document::getId).orElseThrow(SystemException::new);
        if (StringUtils.isBlank(difyDocumentId)) {
            throw new SystemException();
        }

        String knowledgeBaseMetadataId = sysTenantDifyRegister.datasetMetadata()
                .get(DatasetMetadata.NEURON_KNOWLEDGE_BASE_ID.getName());
        UpdateDocumentMetadataRequest request = UpdateDocumentMetadataRequest.builder()
                .operationData(
                        List.of(UpdateDocumentMetadataRequest.OperationData.builder()
                                .documentId(difyDocumentId)
                                .metadataList(
                                        List.of(
                                                UpdateDocumentMetadataRequest.MetadataItem.builder()
                                                        .id(knowledgeBaseMetadataId)
                                                        .name(DatasetMetadata.NEURON_KNOWLEDGE_BASE_ID.getName())
                                                        .value(knowledgeBase.getId().value())
                                                        .build()
                                        )
                                )
                                .build()
                        )
                ).build();
        difyDatasetsClient.updateDocumentMetadata(sysTenantDifyRegister.datasetId(), request);
        boolean saved = difyDocumentRepository.save(knowledgeBase.getId(), document.getId(),
                sysTenantDifyRegister.datasetId(), difyDocumentId);
        if (!saved) {
            throw new ResourceAlreadyExistException();
        }
    }

    @Transactional
    @Override
    public void deleteDocument(KnowledgeBase knowledgeBase, Document document) {
        DifyDocument difyDocument = difyDocumentRepository.fetchExternalInfo(knowledgeBase.getId(),
                document.getId()).orElseThrow(ResourceNotFoundException::new);
        boolean deleted = difyDocumentRepository.delete(difyDocument);
        if (!deleted) {
            throw new ResourceNotFoundException();
        }
        difyDatasetsClient.deleteDocument(difyDocument.difyDatasetId().value(), difyDocument.difyDocumentId().value());
    }

    @Override
    public KnowledgeBaseImpl impl() {
        return KnowledgeBaseImpl.DIFY;
    }

}
