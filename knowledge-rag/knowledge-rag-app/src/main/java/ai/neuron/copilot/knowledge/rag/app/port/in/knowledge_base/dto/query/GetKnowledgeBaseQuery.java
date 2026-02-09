package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public record GetKnowledgeBaseQuery(KnowledgeBaseId id, TenantId tenantId) {
}
