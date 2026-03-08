package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat;

import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.foundation.core.json.SnakeCaseJsonCodec;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.DifyChatCallbackHandler;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.DifyChatEvent;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.AbstractChatEventDTO;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.handler.DifyChatMessageHandlerDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DifyChatCallbackHandlerImpl implements DifyChatCallbackHandler {

    private final SnakeCaseJsonCodec snakeCaseJsonCodec;

    private final ConversationMessageSender conversationMessageSender;

    private final DifyChatMessageHandlerDispatcher difyChatMessageHandlerDispatcher;

    @Override
    public void handleOnOpen(DifyChatCallbackContext context) {
    }

    @Override
    public void handleOnMessage(DifyChatCallbackContext context, String event, String data) {
        System.out.println("on message " + event + " " + data);
        if (event != null && !"message".equals(event) ) {
            return;
        }
        AbstractChatEventDTO dto = snakeCaseJsonCodec.decode(data, AbstractChatEventDTO.class);
        DifyChatEvent difyChatEvent = DifyChatEvent.fromEvent(dto.getEvent());
        difyChatMessageHandlerDispatcher.get(difyChatEvent).dispatch(context, dto);
    }

    @Override
    public void handleOnClose(DifyChatCallbackContext context) {
        System.out.println("on close " + context.getServerId());
        conversationMessageSender.complete(context);
    }

    @Override
    public void handleOnError(DifyChatCallbackContext context, Throwable throwable) {
        System.out.println("on error " + throwable);
        conversationMessageSender.error(context, new SystemException(throwable) );
    }

}
