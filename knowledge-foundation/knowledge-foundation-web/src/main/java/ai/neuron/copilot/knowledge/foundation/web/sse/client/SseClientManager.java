package ai.neuron.copilot.knowledge.foundation.web.sse.client;

public interface SseClientManager {

    <C extends SseClientContext> void register(SseRequest sseRequest, C context,
                                                        SseClientListener<C> listener);

    <C extends SseClientContext> void cleanup(String clientId, String reason);

}
