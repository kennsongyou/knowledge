package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;

import java.io.InputStream;

public record CreateDocumentByFileCommand(
        InputStream inputStream,
        FileUploadDTO fileUploadDTO,
        Object payload,
        TenantId tenantId) {
}
