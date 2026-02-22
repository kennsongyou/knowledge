package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base_document;

import ai.neuron.copilot.knowledge.foundation.blob.BlobInputStreamDTO;
import ai.neuron.copilot.knowledge.foundation.blob.BlobObjectKey;
import ai.neuron.copilot.knowledge.foundation.blob.ObjectStorageClient;
import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceAlreadyExistException;
import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.foundation.core.io.FoundationIOUtils;
import ai.neuron.copilot.knowledge.foundation.core.json.FoundationJsonCodec;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.CreateKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.CreateKnowledgeBaseDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.config.DatasetMetadata;
import ai.neuron.copilot.knowledge.rag.app.port.out.config.DifyConfigProvider;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.DifyDatasetsClient;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.CreateDocumentByFileRequestData;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.UpdateDocumentMetadataRequest;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.response.CreateDocumentByFileResponse;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseDocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CreateKnowledgeBaseDocumentService implements CreateKnowledgeBaseDocumentUseCase {

	private final KnowledgeBaseDocumentRepository knowledgeBaseDocumentRepository;

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	private final DocumentRepository documentRepository;

	private final DifyDatasetsClient difyDatasetsClient;

	private final DifyConfigProvider difyConfigProvider;

	private final FoundationJsonCodec foundationJsonCodec;

	private final ObjectStorageClient objectStorageClient;

    @Transactional
	@Override
	public void execute(CreateKnowledgeBaseDocumentCommand command) {
		KnowledgeBase knowledgeBase = knowledgeBaseRepository.fetch(command.knowledgeBaseId())
				.orElseThrow(ResourceNotFoundException::new);
		Document document = documentRepository.fetch(command.documentId()).orElseThrow(ResourceNotFoundException::new);
		boolean exists = knowledgeBaseDocumentRepository.exists(command.knowledgeBaseId(), command.documentId());
		if (exists) {
			throw new ResourceAlreadyExistException();
		}
		boolean saved = knowledgeBaseDocumentRepository.save(command.knowledgeBaseId(), command.documentId());
		if (!saved) {
			throw new SystemException();
		}
		BlobObjectKey blobObjectKey = BlobObjectKey.reconstitute(document.getObjectKey());
		BlobInputStreamDTO blobInputStreamDTO = objectStorageClient.fetch(blobObjectKey);
		String data = foundationJsonCodec.encode(CreateDocumentByFileRequestData.defaultRequest());
		Resource resource = FoundationIOUtils.toResource(blobInputStreamDTO.getInputStream(),
				document.getOriginalFileName(), blobInputStreamDTO.getSize());

		String difyDatasetId = knowledgeBase.getDifyDatasetId().value();
		CreateDocumentByFileResponse response = difyDatasetsClient.createDocumentByFile(difyDatasetId, data, resource);
		String difyDocumentId = Optional.ofNullable(response).map(CreateDocumentByFileResponse::getDocument)
				.map(CreateDocumentByFileResponse.Document::getId).orElseThrow(SystemException::new);
		if (StringUtils.isBlank(difyDocumentId)) {
			throw new SystemException();
		}
		UpdateDocumentMetadataRequest request = UpdateDocumentMetadataRequest.builder()
				.operationData(
						List.of(UpdateDocumentMetadataRequest.OperationData.builder()
								.documentId(difyDocumentId)
								.metadataList(
										List.of(
												UpdateDocumentMetadataRequest.MetadataItem.builder()
														.id(difyConfigProvider.metadataId(DatasetMetadata.NEURON_KNOWLEDGE_BASE_ID))
														.name(DatasetMetadata.NEURON_KNOWLEDGE_BASE_ID.getName())
														.value(command.knowledgeBaseId().value())
														.build()
										)
								)
								.build()
						)
				).build();
		difyDatasetsClient.updateDocumentMetadata(difyDatasetId, request);
	}

}
