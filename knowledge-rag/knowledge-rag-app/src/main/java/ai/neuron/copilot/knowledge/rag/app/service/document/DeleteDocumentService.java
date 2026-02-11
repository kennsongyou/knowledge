package ai.neuron.copilot.knowledge.rag.app.service.document;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.DeleteDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.DeleteDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.context.CurrentOperatorProvider;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteDocumentService implements DeleteDocumentUseCase {

    private final DocumentRepository documentRepository;

    private final CurrentOperatorProvider currentOperatorProvider;

    @Override
    public void execute(DeleteDocumentCommand command) {
        boolean deleted = documentRepository.delete(command.id(), currentOperatorProvider.userId(),
                currentOperatorProvider.tenantId());
        if (!deleted) {
            throw new ResourceNotFoundException();
        }
    }
}
