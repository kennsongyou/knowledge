package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query.PageKnowledgeBaseDocumentQuery;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;

public interface PageKnowledgeBaseDocumentUseCase {

    PageResult<Document> execute(PageKnowledgeBaseDocumentQuery query);

}
