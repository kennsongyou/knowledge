package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBaseDocumentIdPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBaseDocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaKnowledgeBaseDocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseDocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class KnowledgeBaseDocumentRepositoryImpl implements KnowledgeBaseDocumentRepository {

    public final JpaKnowledgeBaseDocumentRepository jpaKnowledgeBaseDocumentRepository;

    @Override
    public boolean exists(KnowledgeBaseId knowledgeBaseId, DocumentId documentId) {
        KnowledgeBaseDocumentIdPO idPO = new KnowledgeBaseDocumentIdPO(
                knowledgeBaseId.value(),
                documentId.value()
        );
        return jpaKnowledgeBaseDocumentRepository.existsByKnowledgeBaseDocumentIdPO(idPO);
    }

    @Override
    public void save(KnowledgeBaseId knowledgeBaseId, DocumentId documentId) {
        KnowledgeBaseDocumentIdPO idPO = new KnowledgeBaseDocumentIdPO(
                knowledgeBaseId.value(),
                documentId.value()
        );
        jpaKnowledgeBaseDocumentRepository.save(new KnowledgeBaseDocumentPO(idPO));
    }

    @Override
    public boolean delete(KnowledgeBaseId knowledgeBaseId, DocumentId documentId) {
        KnowledgeBaseDocumentIdPO idPO = new KnowledgeBaseDocumentIdPO(
                knowledgeBaseId.value(),
                documentId.value()
        );
        return jpaKnowledgeBaseDocumentRepository.findByKnowledgeBaseDocumentIdPO(idPO)
                .map(po -> {
                    jpaKnowledgeBaseDocumentRepository.delete(po);
                    return true;
                }).orElse(false);
    }

}
