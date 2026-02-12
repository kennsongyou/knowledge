package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository;

import ai.neuron.copilot.knowledge.foundation.data.rdb.JpaAuditingRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaKnowledgeBaseRepository extends JpaAuditingRepository<KnowledgeBasePO, String>,
		JpaSpecificationExecutor<KnowledgeBasePO> {

	Optional<KnowledgeBasePO> findByKnowledgeBaseId(String knowledgeBaseId);

	Optional<KnowledgeBasePO> findByName(String name);

	Page<KnowledgeBasePO> findByNameContainingIgnoreCaseOrderByCreatedAtDesc(Pageable pageable, String name);

	Page<KnowledgeBasePO> findByOrderByCreatedAtDesc(Pageable pageable);

}
