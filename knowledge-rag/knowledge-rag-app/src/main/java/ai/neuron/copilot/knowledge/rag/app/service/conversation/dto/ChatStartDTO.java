package ai.neuron.copilot.knowledge.rag.app.service.conversation.dto;

import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;

public record ChatStartDTO(
        boolean firstMessage,
        Conversation conversation,
        KnowledgeBase knowledgeBase,
        String serverId) {
}
