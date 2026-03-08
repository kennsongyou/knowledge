package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.ChatEventErrorDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DifyChatEventErrorHandler implements DifyChatMessageHandler<ChatEventErrorDTO> {

    private final ConversationMessageSender conversationMessageSender;

    @Override
    public void handle(DifyChatCallbackContext context, ChatEventErrorDTO event) {
        System.out.println();
    }

    @Override
    public DifyChatEvent type() {
        return DifyChatEvent.ERROR;
    }

}
