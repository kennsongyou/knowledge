package ai.neuron.copilot.knowledge.rag.app.port.in.document;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.PageDocumentQuery;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;

public interface PageDocumentUseCase {

    PageResult<Document> execute(PageDocumentQuery query);

}
