package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query;

import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public record PageKnowledgeBaseDocumentQuery(PageQuery pageQuery, KnowledgeBaseId knowledgeBaseId) {
}
