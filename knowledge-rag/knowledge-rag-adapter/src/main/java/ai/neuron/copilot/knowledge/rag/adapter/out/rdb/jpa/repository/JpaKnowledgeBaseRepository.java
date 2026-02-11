package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository;

import ai.neuron.copilot.knowledge.foundation.data.rdb.JpaBaseRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaKnowledgeBaseRepository extends JpaBaseRepository<KnowledgeBasePO, Long>,
		JpaSpecificationExecutor<KnowledgeBasePO> {

	Optional<KnowledgeBasePO> findByKnowledgeBaseIdAndTenantId(String knowledgeBaseId, Long tenantId);

	Optional<KnowledgeBasePO> findByNameAndTenantId(String name, Long tenantId);

	Page<KnowledgeBasePO> findByTenantIdAndNameContainingIgnoreCaseOrderByCreatedAtDesc(Pageable pageable,
																						Long tenantId, String name);
	Page<KnowledgeBasePO> findByTenantIdOrderByCreatedAtDesc(Pageable pageable, Long tenantId);

	@Modifying
	@Query(value = """
        UPDATE knowledge_base
        SET
            deleted = 1,
            updated_by = :user_id,
            updated_at = CURRENT_TIMESTAMP
        WHERE
            knowledge_base_id = :knowledge_base_id
            AND tenant_id = :tenant_id
            AND deleted = 0
        """,
			nativeQuery = true)
	int softDelete(
			@Param("knowledge_base_id") String knowledgeBaseId,
			@Param("user_id") long userId,
			@Param("tenant_id") long tenantId
	);

}
