package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter.DocumentConverter;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.KnowledgeBaseDocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.KnowledgeBaseDocumentPORepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseDocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class KnowledgeBaseDocumentRepositoryImpl implements KnowledgeBaseDocumentRepository {

    private final KnowledgeBaseDocumentPORepository knowledgeBaseDocumentPORepository;

    @Override
    public boolean exists(KnowledgeBaseId knowledgeBaseId, DocumentId documentId) {
        return knowledgeBaseDocumentPORepository.lambdaQuery()
                .eq(KnowledgeBaseDocumentPO::getKnowledgeBaseId, knowledgeBaseId.value())
                .eq(KnowledgeBaseDocumentPO::getDocumentId, documentId.value())
                .exists();
    }

    @Override
    public boolean save(KnowledgeBaseId knowledgeBaseId, DocumentId documentId) {
        KnowledgeBaseDocumentPO po = new KnowledgeBaseDocumentPO();
        po.setKnowledgeBaseId(knowledgeBaseId.value());
        po.setDocumentId(documentId.value());
        return knowledgeBaseDocumentPORepository.save(po);
    }

    @Override
    public boolean delete(KnowledgeBaseId knowledgeBaseId, DocumentId documentId) {
        return knowledgeBaseDocumentPORepository.lambdaUpdate()
                .eq(KnowledgeBaseDocumentPO::getKnowledgeBaseId, knowledgeBaseId.value())
                .eq(KnowledgeBaseDocumentPO::getDocumentId, documentId.value())
                .remove();
    }

}
