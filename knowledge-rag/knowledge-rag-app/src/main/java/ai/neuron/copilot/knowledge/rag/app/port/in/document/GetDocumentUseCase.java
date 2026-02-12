package ai.neuron.copilot.knowledge.rag.app.port.in.document;

import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.FetchDocumentUrlQuery;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;

public interface GetDocumentUseCase {

    Document execute(GetDocumentQuery query);

    String accessUrl(FetchDocumentUrlQuery query);

}
