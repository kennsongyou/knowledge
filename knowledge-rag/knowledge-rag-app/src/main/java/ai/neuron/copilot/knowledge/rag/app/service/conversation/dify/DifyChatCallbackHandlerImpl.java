package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify;

import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.DifyChatCallbackHandler;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.dto.response.ConversationOutMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DifyChatCallbackHandlerImpl implements DifyChatCallbackHandler {

    private final ConversationMessageSender conversationMessageSender;

    @Override
    public void handleOnOpen(String serverId) {
    }

    @Override
    public void handleOnMessage(String serverId, String event, String data) {
        System.out.println("on message " + event + " " + data);
        conversationMessageSender.send(serverId, ConversationOutMessage.builder().data(data).build());
    }

    @Override
    public void handleOnClose(String serverId) {
        System.out.println("on close " + serverId);
        conversationMessageSender.complete(serverId);

    }

    @Override
    public void handleOnError(String serverId, Throwable throwable) {
        System.out.println("on error " + throwable);
        conversationMessageSender.error(serverId, new SystemException(throwable) );
    }

}
