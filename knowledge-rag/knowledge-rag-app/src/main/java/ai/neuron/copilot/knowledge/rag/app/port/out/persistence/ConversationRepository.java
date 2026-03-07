package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.data.page.CursorQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.CursorResult;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;

public interface ConversationRepository {

    CursorResult<Conversation> cursor(CursorQuery query);

    boolean save(Conversation conversation);

    boolean delete(ConversationId conversationId);

}
