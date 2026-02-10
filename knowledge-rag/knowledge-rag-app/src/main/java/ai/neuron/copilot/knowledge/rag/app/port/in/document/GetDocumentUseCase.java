package ai.neuron.copilot.knowledge.rag.app.port.in.document;

import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentUrlQuery;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;

public interface GetDocumentUseCase {

    Document execute(GetDocumentQuery query);

    String accessUrl(GetDocumentUrlQuery query);

}
