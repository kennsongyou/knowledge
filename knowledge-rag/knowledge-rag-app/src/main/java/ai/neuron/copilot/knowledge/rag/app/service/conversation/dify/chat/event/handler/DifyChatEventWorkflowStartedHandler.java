package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.ChatEventWorkflowStartedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DifyChatEventWorkflowStartedHandler implements DifyChatMessageHandler<ChatEventWorkflowStartedDTO> {

    private final ConversationMessageSender conversationMessageSender;

    @Override
    public void handle(String serverId, ChatEventWorkflowStartedDTO event) {

    }

    @Override
    public DifyChatEvent type() {
        return DifyChatEvent.WORKFLOW_STARTED;
    }

}
