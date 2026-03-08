package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.ChatEventNodeStartedDTO;
import org.springframework.stereotype.Component;

@Component
public class DifyChatEventNodeStartedHandler implements DifyChatMessageHandler<ChatEventNodeStartedDTO> {

    @Override
    public void handle(DifyChatCallbackContext context, ChatEventNodeStartedDTO event) {

    }

    @Override
    public DifyChatEvent type() {
        return DifyChatEvent.NODE_STARTED;
    }

}
