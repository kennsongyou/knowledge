package ai.neuron.copilot.knowledge.rag.adapter.out.http.sse;

import ai.neuron.copilot.knowledge.foundation.core.exception.BaseException;
import ai.neuron.copilot.knowledge.foundation.web.sse.server.SseServerManager;
import ai.neuron.copilot.knowledge.foundation.web.sse.server.SseServerMessage;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.dto.response.ConversationOutMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ConversationMessageSenderImpl implements ConversationMessageSender {

    private final SseServerManager registry;

    @Override
    public void send(String connectionId, ConversationOutMessage message) {
        SseServerMessage sseServerMessage = SseServerMessage.success("rag", message.getData());
        registry.send(connectionId, sseServerMessage);
    }

    @Override
    public void complete(String connectionId) {
        ConversationOutMessage message = ConversationOutMessage.builder().data("completed").build();
        send(connectionId, message);
        registry.connectionComplete(connectionId);
    }


    @Override
    public void error(String connectionId, BaseException ex) {
        ConversationOutMessage message = ConversationOutMessage.builder().data("error").build();
        send(connectionId, message);
        registry.connectionError(connectionId, ex);
    }

}