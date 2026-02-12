package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query;

import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;

public record FetchDocumentUrlQuery(DocumentId id) {
}
