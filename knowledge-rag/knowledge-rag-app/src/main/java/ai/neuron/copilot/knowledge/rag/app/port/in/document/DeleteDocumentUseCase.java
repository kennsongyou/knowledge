package ai.neuron.copilot.knowledge.rag.app.port.in.document;

import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.DeleteDocumentCommand;

public interface DeleteDocumentUseCase {

    void execute(DeleteDocumentCommand command);

}
