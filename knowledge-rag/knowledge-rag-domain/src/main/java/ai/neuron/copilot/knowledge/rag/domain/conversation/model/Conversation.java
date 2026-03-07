package ai.neuron.copilot.knowledge.rag.domain.conversation.model;

public record Conversation (
    ConversationId id,
    ConversationName name
) {
    public static Conversation reconstitute(ConversationId id, ConversationName name) {
        return new Conversation(id, name);
    }
    public static Conversation create(ConversationName name) {
        return new Conversation(ConversationId.create(), name);
    }
}
