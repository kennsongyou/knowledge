package ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command;

import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationName;

public record UpdateConversationCommand(
        ConversationId id,
        ConversationName name,
        String metadata
) {}
