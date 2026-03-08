package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.ChatEventMessageEndDTO;
import org.springframework.stereotype.Component;

@Component
public class DifyChatEventMessageEndHandler implements DifyChatMessageHandler<ChatEventMessageEndDTO> {

    @Override
    public void handle(DifyChatCallbackContext context, ChatEventMessageEndDTO event) {

    }

    @Override
    public DifyChatEvent type() {
        return DifyChatEvent.MESSAGE_END;
    }

}
