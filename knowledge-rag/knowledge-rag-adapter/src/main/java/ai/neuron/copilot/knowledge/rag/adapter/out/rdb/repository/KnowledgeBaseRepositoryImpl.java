package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaKnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Repository
public class KnowledgeBaseRepositoryImpl implements KnowledgeBaseRepository {

    public final JpaKnowledgeBaseRepository jpaKnowledgeBaseRepository;

    @Override
    public void create(KnowledgeBase knowledgeBase) {
        KnowledgeBasePO po = new KnowledgeBasePO();
        po.setKnowledgeBaseId(knowledgeBase.getKnowledgeBaseId().value());
        po.setName(knowledgeBase.getName());
        po.setDescription(knowledgeBase.getDescription());
        po.setDifyDatasetId(knowledgeBase.getDifyDatasetId().value());
        jpaKnowledgeBaseRepository.save(po);
    }

    @Override
    public void delete(KnowledgeBaseId knowledgeBaseId) {
        jpaKnowledgeBaseRepository.findByKnowledgeBaseId(knowledgeBaseId.value())
                .ifPresent(e -> {
                    e.setDeletedAt(LocalDateTime.now());
                    jpaKnowledgeBaseRepository.save(e);
                });
    }

}
