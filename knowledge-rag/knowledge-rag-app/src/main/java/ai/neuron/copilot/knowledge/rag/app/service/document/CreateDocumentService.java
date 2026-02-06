package ai.neuron.copilot.knowledge.rag.app.service.document;

import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DocumentId;
import org.springframework.stereotype.Service;

@Service
public class CreateDocumentService implements CreateDocumentUseCase {

    @Override
    public DocumentId byFile(CreateDocumentByFileCommand command) {
        return DocumentId.create();
    }

}
