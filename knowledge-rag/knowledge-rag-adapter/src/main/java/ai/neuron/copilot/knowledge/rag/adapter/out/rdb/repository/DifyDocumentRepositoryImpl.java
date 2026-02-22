package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DifyDocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.DifyDocumentPORepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DifyDocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDatasetId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDocument;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class DifyDocumentRepositoryImpl implements DifyDocumentRepository {

    private final DifyDocumentPORepository difyDocumentPORepository;

    @Override
    public boolean save(KnowledgeBaseId knowledgeBaseId, DocumentId documentId, String externalDatasetId,
                        String externalDocumentId) {
        DifyDocumentPO po = new DifyDocumentPO();
        po.setDifyDocumentId(IdUtils.trimmedUuidV7());
        po.setDocumentId(documentId.value());
        po.setKnowledgeBaseId(knowledgeBaseId.value());
        po.setExternalDocumentId(externalDocumentId);
        po.setExternalDatasetId(externalDatasetId);
        return difyDocumentPORepository.save(po);
    }

    @Override
    public List<DifyDocument> listByKnowledgeBaseId(KnowledgeBaseId knowledgeBaseId) {
        List<DifyDocumentPO> pos = difyDocumentPORepository.lambdaQuery()
                .eq(DifyDocumentPO::getKnowledgeBaseId, knowledgeBaseId.value())
                .list();
        return pos.stream().map(e -> {
            DifyDatasetId difyDatasetId = DifyDatasetId.reconstitute(e.getExternalDatasetId());
            DifyDocumentId difyDocumentId = DifyDocumentId.reconstitute(e.getExternalDocumentId());
            return DifyDocument.reconstitute(difyDatasetId, difyDocumentId);
        }).toList();
    }

    @Override
    public void deleteByKnowledgeBaseId(KnowledgeBaseId knowledgeBaseId) {
        difyDocumentPORepository.lambdaUpdate()
                .eq(DifyDocumentPO::getKnowledgeBaseId, knowledgeBaseId.value())
                .remove();
    }

    @Override
    public Optional<DifyDocument> fetchExternalInfo(KnowledgeBaseId knowledgeBaseId, DocumentId documentId) {
        return difyDocumentPORepository.lambdaQuery()
                .eq(DifyDocumentPO::getKnowledgeBaseId, knowledgeBaseId.value())
                .eq(DifyDocumentPO::getDocumentId, documentId.value())
                .oneOpt().map(e -> {
                    DifyDatasetId difyDatasetId = DifyDatasetId.reconstitute(e.getExternalDatasetId());
                    DifyDocumentId difyDocumentId = DifyDocumentId.reconstitute(e.getExternalDocumentId());
                    return DifyDocument.reconstitute(difyDatasetId, difyDocumentId);
                });
    }

    @Override
    public boolean delete(DifyDocument difyDocument) {
        return difyDocumentPORepository.lambdaUpdate()
                .eq(DifyDocumentPO::getExternalDocumentId, difyDocument.difyDocumentId().value())
                .eq(DifyDocumentPO::getExternalDatasetId, difyDocument.difyDatasetId().value())
                .remove();
    }

    @Override
    public boolean delete(KnowledgeBaseId knowledgeBaseId, DocumentId documentId) {
        return difyDocumentPORepository.lambdaUpdate()
                .eq(DifyDocumentPO::getKnowledgeBaseId, knowledgeBaseId.value())
                .eq(DifyDocumentPO::getDocumentId, documentId.value())
                .remove();
    }

}
