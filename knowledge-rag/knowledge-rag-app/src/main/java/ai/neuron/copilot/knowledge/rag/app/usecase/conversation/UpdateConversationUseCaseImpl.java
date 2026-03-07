package ai.neuron.copilot.knowledge.rag.app.usecase.conversation;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.UpdateConversationUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.UpdateConversationCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.ConversationRepository;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateConversationUseCaseImpl implements UpdateConversationUseCase {

    private final ConversationRepository conversationRepository;

    @Transactional
    @Override
    public void execute(UpdateConversationCommand command) {
        Conversation conversation = conversationRepository.fetch(command.id())
                .orElseThrow(ResourceNotFoundException::new);
        conversation.rename(command.name());
        conversation.updateMetadata(command.metadata());
        boolean updated = conversationRepository.update(conversation);
        if (!updated) {
            throw new SystemException();
        }
    }

}
