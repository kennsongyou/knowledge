package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.AbstractChatEventDTO;

public interface DifyChatMessageHandler<T extends AbstractChatEventDTO> {

    void handle(String serverId, T event);

    DifyChatEvent type();

    @SuppressWarnings("unchecked")
    default void dispatch(String serverId, AbstractChatEventDTO event) {
        System.out.println(this.type().name());
        handle(serverId, (T) event);
    }

}
