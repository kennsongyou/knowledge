package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;

public record PageDocumentQuery(String keyword, PageQuery pageQuery, TenantId tenantId) {
}
