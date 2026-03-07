package ai.neuron.copilot.knowledge.rag.app.usecase.conversation;

import ai.neuron.copilot.knowledge.foundation.data.page.CursorQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.CursorResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.CursorConversationUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.ConversationRepository;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CursorConversationUseCaseImpl implements CursorConversationUseCase {

    private final ConversationRepository conversationRepository;

    @Override
    public CursorResult<Conversation> execute(CursorQuery query) {
        return conversationRepository.cursor(query);
    }

}
