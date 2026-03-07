package ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command;

public record AddMessageCommand(
        String conversationId,
        String connectionId,
        String query
) {}
