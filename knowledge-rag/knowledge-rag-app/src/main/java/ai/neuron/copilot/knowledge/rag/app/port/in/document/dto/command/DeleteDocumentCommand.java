package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;

public record DeleteDocumentCommand(
        DocumentId id,
        TenantId tenantId) {
}
