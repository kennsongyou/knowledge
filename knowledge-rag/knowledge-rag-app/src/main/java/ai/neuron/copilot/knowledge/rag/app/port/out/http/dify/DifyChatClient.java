package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify;

public interface DifyChatClient {

    void chat(String conversationId, String query);

}
