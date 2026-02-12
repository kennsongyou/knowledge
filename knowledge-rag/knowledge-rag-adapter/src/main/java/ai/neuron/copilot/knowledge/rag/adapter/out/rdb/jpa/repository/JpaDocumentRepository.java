package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository;

import ai.neuron.copilot.knowledge.foundation.data.rdb.JpaAuditingRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.DocumentPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaDocumentRepository extends JpaAuditingRepository<DocumentPO, String>,
		JpaSpecificationExecutor<DocumentPO> {

	Optional<DocumentPO> findByDocumentId(String documentId);

	Page<DocumentPO> findByDisplayNameContainingIgnoreCaseOrderByCreatedAtDesc(Pageable pageable, String name);

	Page<DocumentPO> findByOrderByCreatedAtDesc(Pageable pageable);

}
