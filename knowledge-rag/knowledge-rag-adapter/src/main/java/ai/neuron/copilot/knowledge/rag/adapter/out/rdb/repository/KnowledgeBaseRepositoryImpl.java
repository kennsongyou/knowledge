package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaKnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class KnowledgeBaseRepositoryImpl implements KnowledgeBaseRepository {

    public final JpaKnowledgeBaseRepository jpaRepository;

    @Override
    public void save(KnowledgeBase knowledgeBase) {
        KnowledgeBasePO po = new KnowledgeBasePO();
        po.setKnowledgeBaseId(knowledgeBase.getKnowledgeBaseId().value());
        po.setName(knowledgeBase.getName());
        po.setDescription(knowledgeBase.getDescription());
        po.setDifyDatasetId(knowledgeBase.getDifyDatasetId().value());
        jpaRepository.save(po);
    }

}
