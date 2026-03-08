package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.ChatEventPingDTO;
import org.springframework.stereotype.Component;

@Component
public class DifyChatEventPingHandler implements DifyChatMessageHandler<ChatEventPingDTO> {

    @Override
    public void handle(DifyChatCallbackContext context, ChatEventPingDTO event) {

    }

    @Override
    public DifyChatEvent type() {
        return DifyChatEvent.PING;
    }

}
