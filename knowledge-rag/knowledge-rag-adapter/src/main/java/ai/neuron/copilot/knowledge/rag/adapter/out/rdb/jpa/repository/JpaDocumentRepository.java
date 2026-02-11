package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository;

import ai.neuron.copilot.knowledge.foundation.data.rdb.JpaBaseRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.DocumentPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaDocumentRepository extends JpaBaseRepository<DocumentPO, Long>,
		JpaSpecificationExecutor<DocumentPO> {

	Optional<DocumentPO> findByDocumentIdAndTenantId(String documentId, Long tenantId);

	Page<DocumentPO> findByTenantIdAndDisplayNameContainingIgnoreCaseOrderByCreatedAtDesc(Pageable pageable,
																						Long tenantId, String name);
	Page<DocumentPO> findByTenantIdOrderByCreatedAtDesc(Pageable pageable, Long tenantId);

	@Modifying
	@Query(value = """
        UPDATE document
        SET
            deleted = 1,
            updated_by = :user_id,
            updated_at = CURRENT_TIMESTAMP
        WHERE
            document_id = :document_id
            AND tenant_id = :tenant_id
            AND deleted = 0
        """,
			nativeQuery = true)
	int softDelete(
			@Param("document_id") String documentId,
			@Param("user_id") long userId,
			@Param("tenant_id") long tenantId
	);

}
