package ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.mysql.repository;

import ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.mysql.entity.KnowledgeBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBaseEntity, Long> {

	// 根据 knowledgeBaseId 查询
	Optional<KnowledgeBaseEntity> findByKnowledgeBaseId(String knowledgeBaseId);

}
