package ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation;

import ai.neuron.copilot.knowledge.foundation.core.exception.BaseException;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.dto.response.ConversationOutMessage;

public interface ConversationMessageSender {

    void connected(DifyChatCallbackContext context);

    void send(DifyChatCallbackContext context, ConversationOutMessage message);

    void complete(DifyChatCallbackContext context);

    void error(DifyChatCallbackContext context, BaseException ex);

}