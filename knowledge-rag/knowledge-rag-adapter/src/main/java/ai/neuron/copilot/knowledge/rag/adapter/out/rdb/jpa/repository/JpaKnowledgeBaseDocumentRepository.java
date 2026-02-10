package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository;

import ai.neuron.copilot.knowledge.foundation.data.rdb.BaseRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBaseDocumentIdPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBaseDocumentPO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JpaKnowledgeBaseDocumentRepository extends BaseRepository<KnowledgeBaseDocumentPO, KnowledgeBaseDocumentIdPO>,
		JpaSpecificationExecutor<KnowledgeBaseDocumentPO> {

	Optional<KnowledgeBaseDocumentPO> findByKnowledgeBaseDocumentIdPO(KnowledgeBaseDocumentIdPO knowledgeBaseDocumentIdPO);

	boolean existsByKnowledgeBaseDocumentIdPO(KnowledgeBaseDocumentIdPO knowledgeBaseDocumentIdPO);

}
