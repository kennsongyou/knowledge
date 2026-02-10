package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query.ExistsKnowledgeBaseDocumentQuery;

public interface ExistsKnowledgeBaseDocumentUseCase {

    void requireRelationExists(ExistsKnowledgeBaseDocumentQuery query);

}
