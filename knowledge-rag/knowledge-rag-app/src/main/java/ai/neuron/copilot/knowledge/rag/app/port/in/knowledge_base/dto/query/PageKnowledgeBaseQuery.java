package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;

public record PageKnowledgeBaseQuery(TenantId tenantId, String keyword, PageQuery pageQuery) {
}
