package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.AbstractChatEventDTO;

public interface DifyChatMessageHandler<T extends AbstractChatEventDTO> {

    void handle(DifyChatCallbackContext context, T event);

    DifyChatEvent type();

    @SuppressWarnings("unchecked")
    default void dispatch(DifyChatCallbackContext context, AbstractChatEventDTO event) {
        System.out.println(this.type().name());
        handle(context, (T) event);
    }

}
