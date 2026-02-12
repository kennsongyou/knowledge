package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.DeleteKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.DeleteKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteKnowledgeBaseService implements DeleteKnowledgeBaseUseCase {

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    @Transactional
    @Override
    public void execute(DeleteKnowledgeBaseCommand command) {
        boolean deleted = knowledgeBaseRepository.delete(command.id());
        if (!deleted) {
            throw new ResourceNotFoundException();
        }
    }

}
