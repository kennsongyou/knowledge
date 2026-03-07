package ai.neuron.copilot.knowledge.rag.domain.conversation.model;

import org.apache.commons.lang3.StringUtils;

public record ConversationMessageContent(String value) {

    public ConversationMessageContent {
        if (StringUtils.length(value) > 65535) {
            throw new IllegalArgumentException("Conversation message content is too long (max 65535 characters)");
        }
    }

    public static ConversationMessageContent reconstitute(String value) {
        return new ConversationMessageContent(value);
    }

    public static ConversationMessageContent create(String value) {
        return new ConversationMessageContent(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
