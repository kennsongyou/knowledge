package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDocument;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

import java.util.List;
import java.util.Optional;

public interface DifyDocumentRepository {

    boolean save(KnowledgeBaseId knowledgeBaseId, DocumentId documentId, String externalDatasetId,
                 String externalDocumentId);

    List<DifyDocument> listByKnowledgeBaseId(KnowledgeBaseId knowledgeBaseId);

    void deleteByKnowledgeBaseId(KnowledgeBaseId knowledgeBaseId);

    Optional<DifyDocument> fetchExternalInfo(KnowledgeBaseId knowledgeBaseId, DocumentId documentId);

    boolean delete(DifyDocument difyDocument);

    boolean delete(KnowledgeBaseId knowledgeBaseId, DocumentId documentId);

}
