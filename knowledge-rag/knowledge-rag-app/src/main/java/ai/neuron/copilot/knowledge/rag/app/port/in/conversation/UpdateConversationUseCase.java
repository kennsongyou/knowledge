package ai.neuron.copilot.knowledge.rag.app.port.in.conversation;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.UpdateConversationCommand;

public interface UpdateConversationUseCase {

    void execute(UpdateConversationCommand command);

}
