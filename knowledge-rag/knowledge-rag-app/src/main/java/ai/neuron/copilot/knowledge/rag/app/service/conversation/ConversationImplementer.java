package ai.neuron.copilot.knowledge.rag.app.service.conversation;

import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;

import java.util.function.Function;

public interface ConversationImplementer {

    void start(boolean firstMessage, Conversation conversation, String sseServerId,
               Function<Conversation, Boolean> updateConversationFunc);

    KnowledgeBaseImpl impl();
}
