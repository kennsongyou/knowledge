package ai.neuron.copilot.knowledge.foundation.web.sse.client;

public interface SseClientListener<C extends SseClientContext> {

    void onOpen(C context);

    void onMessage(C context, SseEvent event);

    void onError(C context, Throwable t);

    void onClosed(C context);
}
