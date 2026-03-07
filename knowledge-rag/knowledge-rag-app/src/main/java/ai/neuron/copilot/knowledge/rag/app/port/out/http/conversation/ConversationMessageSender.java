package ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation;

import ai.neuron.copilot.knowledge.foundation.core.exception.BaseException;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.dto.response.ConversationOutMessage;

public interface ConversationMessageSender {

    void send(String serverId, ConversationOutMessage message);

    void complete(String serverId);

    void error(String serverId, BaseException ex);

}