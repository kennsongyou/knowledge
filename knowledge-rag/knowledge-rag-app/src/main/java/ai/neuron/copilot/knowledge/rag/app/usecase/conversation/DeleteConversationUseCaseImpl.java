package ai.neuron.copilot.knowledge.rag.app.usecase.conversation;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.DeleteConversationUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.DeleteConversationCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteConversationUseCaseImpl implements DeleteConversationUseCase {

    private final ConversationRepository conversationRepository;

    @Override
    public void execute(DeleteConversationCommand command) {
        boolean deleted = conversationRepository.delete(command.conversationId());
        if (!deleted) {
            throw new ResourceNotFoundException();
        }
    }

}
