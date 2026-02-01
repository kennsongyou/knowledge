package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository;

import ai.neuron.copilot.knowledge.foundation.data.rdb.BaseRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaKnowledgeBaseRepository extends BaseRepository<KnowledgeBasePO, Long> {

	Optional<KnowledgeBasePO> findByKnowledgeBaseId(String knowledgeBaseId);

}
