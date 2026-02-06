package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command;

import ai.neuron.copilot.knowledge.common.io.InputStreamDTO;

public record CreateDocumentByFileCommand(String payload, InputStreamDTO inputStreamDTO) {
}
