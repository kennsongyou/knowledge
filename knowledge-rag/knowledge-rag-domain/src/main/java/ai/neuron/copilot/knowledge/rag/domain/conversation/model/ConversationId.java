package ai.neuron.copilot.knowledge.rag.domain.conversation.model;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import org.apache.commons.lang3.StringUtils;

public record ConversationId(String value) {

    public ConversationId {
        if (StringUtils.length(value) > 255) {
            throw new IllegalArgumentException("Conversation id is too long (max 255 characters)");
        }
    }

    public static ConversationId reconstitute(String value) {
        return new ConversationId(value);
    }

    public static ConversationId create() {
        return new ConversationId(IdUtils.uuidV7Str());
    }

    @Override
    public String toString() {
        return value;
    }

}
