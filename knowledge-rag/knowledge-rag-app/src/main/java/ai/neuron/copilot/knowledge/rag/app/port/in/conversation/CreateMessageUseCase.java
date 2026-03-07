package ai.neuron.copilot.knowledge.rag.app.port.in.conversation;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.CreateMessageCommand;

public interface CreateMessageUseCase {

    void execute(CreateMessageCommand command);

}
