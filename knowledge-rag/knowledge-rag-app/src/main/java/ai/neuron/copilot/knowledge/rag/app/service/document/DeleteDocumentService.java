package ai.neuron.copilot.knowledge.rag.app.service.document;

import ai.neuron.copilot.knowledge.rag.app.port.in.document.DeleteDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.DeleteDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteDocumentService implements DeleteDocumentUseCase {

    private final DocumentRepository documentRepository;

    @Override
    public void execute(DeleteDocumentCommand command) {
        documentRepository.delete(command.id(), command.tenantId());
    }
}
