package ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify;

import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;

public interface DifyChatCallbackHandler {

    void handleOnOpen(DifyChatCallbackContext context);

    void handleOnMessage(DifyChatCallbackContext context, String event, String data);

    void handleOnClose(DifyChatCallbackContext context);

    void handleOnError(DifyChatCallbackContext context, Throwable throwable);

}
