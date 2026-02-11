package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command;

import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;

public record DeleteDocumentCommand(DocumentId id) {
}
