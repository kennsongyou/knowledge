package ai.neuron.copilot.knowledge.rag.app.port.in.conversation;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.AddMessageCommand;

public interface AddMessageUseCase {

    void execute(AddMessageCommand command);

}
