package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.ChatEventWorkflowFinishedDTO;
import org.springframework.stereotype.Component;

@Component
public class DifyChatEventWorkflowFinishedHandler implements DifyChatMessageHandler<ChatEventWorkflowFinishedDTO> {

    @Override
    public void handle(String serverId, ChatEventWorkflowFinishedDTO event) {

    }

    @Override
    public DifyChatEvent type() {
        return DifyChatEvent.WORKFLOW_FINISHED;
    }

}
