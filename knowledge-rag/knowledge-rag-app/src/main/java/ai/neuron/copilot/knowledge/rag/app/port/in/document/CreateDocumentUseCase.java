package ai.neuron.copilot.knowledge.rag.app.port.in.document;

import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DocumentId;

public interface CreateDocumentUseCase {

    DocumentId byFile(CreateDocumentByFileCommand command);

}
