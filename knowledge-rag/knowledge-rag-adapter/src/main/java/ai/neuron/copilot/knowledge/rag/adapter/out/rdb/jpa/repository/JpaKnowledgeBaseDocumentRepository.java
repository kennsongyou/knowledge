package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository;

import ai.neuron.copilot.knowledge.foundation.data.rdb.JpaAuditingRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBaseDocumentEmbeddedId;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBaseDocumentPO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JpaKnowledgeBaseDocumentRepository extends JpaAuditingRepository<KnowledgeBaseDocumentPO, KnowledgeBaseDocumentEmbeddedId>,
		JpaSpecificationExecutor<KnowledgeBaseDocumentPO> {

	Optional<KnowledgeBaseDocumentPO> findByKnowledgeBaseDocumentEmbeddedId(KnowledgeBaseDocumentEmbeddedId knowledgeBaseDocumentIdPO);

	boolean existsByKnowledgeBaseDocumentEmbeddedId(KnowledgeBaseDocumentEmbeddedId knowledgeBaseDocumentIdPO);

}
