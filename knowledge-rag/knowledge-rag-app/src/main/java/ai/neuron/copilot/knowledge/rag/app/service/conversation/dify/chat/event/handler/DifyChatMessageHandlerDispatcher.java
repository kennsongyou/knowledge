package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler;

import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.AbstractChatEventDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DifyChatMessageHandlerDispatcher {

    private final Map<DifyChatEvent, DifyChatMessageHandler<? extends AbstractChatEventDTO>> handlerMap;

    public DifyChatMessageHandlerDispatcher(List<DifyChatMessageHandler<? extends AbstractChatEventDTO>> handlerMap) {
        this.handlerMap = handlerMap.stream()
                .collect(Collectors.toUnmodifiableMap(
                        DifyChatMessageHandler::type,
                        Function.identity()
                ));
    }

    public DifyChatMessageHandler<? extends AbstractChatEventDTO> get(DifyChatEvent type) {
        DifyChatMessageHandler<? extends AbstractChatEventDTO> handler = handlerMap.get(type);
        if (handler == null) {
            throw new IllegalArgumentException("Unsupported dify chat event: " + type);
        }
        return handler;
    }

}
