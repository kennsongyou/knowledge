package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public interface KnowledgeBaseDocumentRepository {

    boolean exists(KnowledgeBaseId knowledgeBaseId, DocumentId documentId);

    void save(KnowledgeBaseId knowledgeBaseId, DocumentId documentId);

    boolean delete(KnowledgeBaseId knowledgeBaseId, DocumentId documentId);

}
