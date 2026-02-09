package ai.neuron.copilot.knowledge.rag.app.port.in.document.dto;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.PageDocumentQuery;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.Document;

public interface PageDocumentUseCase {

    PageResult<Document> execute(PageDocumentQuery query);

}
