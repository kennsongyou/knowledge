package ai.neuron.copilot.knowledge.rag.adapter.out.http.sse;

import ai.neuron.copilot.knowledge.foundation.core.exception.BaseException;
import ai.neuron.copilot.knowledge.foundation.web.sse.server.SseServerManager;
import ai.neuron.copilot.knowledge.foundation.web.sse.server.SseServerMessage;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.dto.response.ConversationOutMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ConversationMessageSenderImpl implements ConversationMessageSender {

    private final SseServerManager manager;

    @Override
    public void connected(DifyChatCallbackContext context) {
        SseServerMessage sseServerMessage = SseServerMessage.create("kbqa", "task_started");
        manager.send(context.getServerId(), sseServerMessage);
    }

    @Override
    public void send(DifyChatCallbackContext context, ConversationOutMessage message) {
        SseServerMessage sseServerMessage = SseServerMessage.create("kbqa", message.getAction().getName(), message.getData());
        manager.send(context.getServerId(), sseServerMessage);
    }

    @Override
    public void complete(DifyChatCallbackContext context) {
        SseServerMessage sseServerMessage = SseServerMessage.create("kbqa", "completed");
        manager.send(context.getServerId(), sseServerMessage);
        manager.connectionComplete(context.getServerId());
    }


    @Override
    public void error(DifyChatCallbackContext context, BaseException ex) {
        SseServerMessage sseServerMessage = SseServerMessage.create("kbqa", "error");
        manager.send(context.getServerId(), sseServerMessage);
        manager.connectionError(context.getServerId(), ex);
    }

}