package ai.neuron.copilot.knowledge.rag.domain.conversation.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public final class Conversation {

    final ConversationId id;

    ConversationName name;

    String metadata;

    public void rename(ConversationName conversationName) {
        this.name = conversationName;
    }

    public void updateMetadata(String metadata) {
        this.metadata = metadata;
    }

    public static Conversation reconstitute(ConversationId id, ConversationName name, String metadata) {
        return new Conversation(id, name, metadata);
    }
    public static Conversation create() {
        return create(ConversationName.create(null), null);
    }
    public static Conversation create(ConversationName name, String metadata) {
        return new Conversation(ConversationId.create(), name, metadata);
    }

}
