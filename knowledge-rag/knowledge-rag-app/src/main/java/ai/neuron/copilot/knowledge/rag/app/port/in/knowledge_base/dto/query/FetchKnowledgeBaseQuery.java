package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public record FetchKnowledgeBaseQuery(KnowledgeBaseId id) {
}
