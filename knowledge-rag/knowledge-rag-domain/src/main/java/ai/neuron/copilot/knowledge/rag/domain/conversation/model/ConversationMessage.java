package ai.neuron.copilot.knowledge.rag.domain.conversation.model;

public record ConversationMessage(
        String id,
        ConversationId conversationId,
        String role,
        ConversationMessageContent content
) {}
