package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.DeleteKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.DeleteKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.context.CurrentOperatorProvider;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteKnowledgeBaseService implements DeleteKnowledgeBaseUseCase {

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    private final CurrentOperatorProvider currentOperatorProvider;

    @Transactional(readOnly = true)
    @Override
    public void execute(DeleteKnowledgeBaseCommand command) {
        boolean deleted = knowledgeBaseRepository.delete(command.id(), currentOperatorProvider.userId(),
                currentOperatorProvider.tenantId());
        if (!deleted) {
            throw new ResourceNotFoundException();
        }
    }

}
