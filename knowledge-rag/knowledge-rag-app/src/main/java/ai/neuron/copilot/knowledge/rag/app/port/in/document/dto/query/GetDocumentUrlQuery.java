package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;

public record GetDocumentUrlQuery(DocumentId id, TenantId tenantId) {
}
