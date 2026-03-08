package ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify;

public interface DifyChatCallbackHandler {

    void handleOnOpen(String serverId);

    void handleOnMessage(String serverId, String event, String data);

    void handleOnClose(String serverId);

    void handleOnError(String serverId, Throwable throwable);

}
