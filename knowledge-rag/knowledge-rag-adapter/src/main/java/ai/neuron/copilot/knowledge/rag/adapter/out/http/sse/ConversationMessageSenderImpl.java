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

    private final SseServerManager manager;

    @Override
    public void connected(String serverId, String conversationId) {
        SseServerMessage sseServerMessage = SseServerMessage.create("kbqa", "task_started");
        manager.send(serverId, sseServerMessage);
    }

    @Override
    public void send(String serverId, ConversationOutMessage message) {
        SseServerMessage sseServerMessage = SseServerMessage.create("kbqa", message.getAction().getName(), message.getData());
        manager.send(serverId, sseServerMessage);
    }

    @Override
    public void complete(String serverId) {
        SseServerMessage sseServerMessage = SseServerMessage.create("kbqa", "completed");
        manager.send(serverId, sseServerMessage);
        manager.connectionComplete(serverId);
    }


    @Override
    public void error(String serverId, BaseException ex) {
        SseServerMessage sseServerMessage = SseServerMessage.create("kbqa", "error");
        manager.send(serverId, sseServerMessage);
        manager.connectionError(serverId, ex);
    }

}