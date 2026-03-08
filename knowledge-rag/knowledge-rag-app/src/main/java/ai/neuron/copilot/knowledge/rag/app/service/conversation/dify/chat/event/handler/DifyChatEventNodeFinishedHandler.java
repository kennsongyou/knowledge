package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.ChatEventNodeFinishedDTO;
import org.springframework.stereotype.Component;

@Component
public class DifyChatEventNodeFinishedHandler implements DifyChatMessageHandler<ChatEventNodeFinishedDTO> {

    @Override
    public void handle(String serverId, ChatEventNodeFinishedDTO event) {

    }

    @Override
    public DifyChatEvent type() {
        return DifyChatEvent.NODE_FINISHED;
    }

}
