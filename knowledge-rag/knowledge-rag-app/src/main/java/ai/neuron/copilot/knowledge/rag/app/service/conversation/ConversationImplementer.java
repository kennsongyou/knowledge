package ai.neuron.copilot.knowledge.rag.app.service.conversation;

import ai.neuron.copilot.knowledge.rag.app.service.conversation.dto.ChatStartDTO;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;

public interface ConversationImplementer {

    void start(ChatStartDTO chatStartDTO);

    KnowledgeBaseImpl impl();
}
