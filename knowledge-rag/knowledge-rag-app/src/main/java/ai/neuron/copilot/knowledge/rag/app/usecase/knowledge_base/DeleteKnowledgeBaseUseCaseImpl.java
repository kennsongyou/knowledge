package ai.neuron.copilot.knowledge.rag.app.usecase.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.DeleteKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.DeleteKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.service.knowledge_base.KnowledgeBaseImplementer;
import ai.neuron.copilot.knowledge.rag.app.service.knowledge_base.KnowledgeBaseImplementerDispatcher;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteKnowledgeBaseUseCaseImpl implements DeleteKnowledgeBaseUseCase {

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    private final KnowledgeBaseImplementerDispatcher knowledgeBaseImplementerDispatcher;

    @Transactional
    @Override
    public void execute(DeleteKnowledgeBaseCommand command) {
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.fetch(command.id())
                .orElseThrow(ResourceNotFoundException::new);
        boolean deleted = knowledgeBaseRepository.delete(knowledgeBase.getId());
        if (!deleted) {
            throw new ResourceNotFoundException();
        }
        knowledgeBaseImplementerDispatcher.get(knowledgeBase.getImpl()).delete(knowledgeBase);
    }

}
