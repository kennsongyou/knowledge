package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SseClient<C extends SseClientContext> {

    private final SseClientConnection connection;

    private final C context;

    private final SseClientListener<C> listener;

    public void cancel() {
        connection.cancel();
    }

}
