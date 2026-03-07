package ai.neuron.copilot.knowledge.rag.domain.conversation.model;

import org.apache.commons.lang3.StringUtils;

public record ConversationName(String value) {

    public ConversationName {
        if (StringUtils.length(value) > 255) {
            throw new IllegalArgumentException("Conversation name is too long (max 255 characters)");
        }
    }

    public static ConversationName reconstitute(String value) {
        return new ConversationName(value);
    }

    public static ConversationName create(String value) {
        return new ConversationName(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
