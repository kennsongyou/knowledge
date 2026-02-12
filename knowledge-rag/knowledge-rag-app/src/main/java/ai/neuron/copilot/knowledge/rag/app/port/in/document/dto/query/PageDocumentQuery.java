package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query;

import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;

public record PageDocumentQuery(PageQuery pageQuery, String keyword) {
}
