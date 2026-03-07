package ai.neuron.copilot.knowledge.rag.app.port.in.conversation;

import ai.neuron.copilot.knowledge.foundation.data.page.CursorQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.CursorResult;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;

public interface CursorConversationUseCase {

    CursorResult<Conversation> execute(CursorQuery query);

}
