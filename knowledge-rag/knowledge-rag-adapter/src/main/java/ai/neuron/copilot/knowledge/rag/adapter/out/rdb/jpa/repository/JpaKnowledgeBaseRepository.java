package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository;

import ai.neuron.copilot.knowledge.foundation.data.rdb.BaseRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaKnowledgeBaseRepository extends BaseRepository<KnowledgeBasePO, Long>,
		JpaSpecificationExecutor<KnowledgeBasePO> {

	Optional<KnowledgeBasePO> findByKnowledgeBaseId(String knowledgeBaseId);

	Page<KnowledgeBasePO> findByTenantIdAndNameContainingIgnoreCaseOrderByCreatedAtDesc(Pageable pageable,
																						Long tenantId, String name);
	Page<KnowledgeBasePO> findByTenantIdOrderByCreatedAtDesc(Pageable pageable, Long tenantId);

}
