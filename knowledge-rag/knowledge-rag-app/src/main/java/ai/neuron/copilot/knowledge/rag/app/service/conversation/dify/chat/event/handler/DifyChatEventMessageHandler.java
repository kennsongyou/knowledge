package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.ChatEventMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class DifyChatEventMessageHandler implements DifyChatMessageHandler<ChatEventMessageDTO> {

    @Override
    public void handle(String serverId, ChatEventMessageDTO event) {

    }

    @Override
    public DifyChatEvent type() {
        return DifyChatEvent.MESSAGE;
    }

}
