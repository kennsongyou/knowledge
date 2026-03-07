package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import lombok.RequiredArgsConstructor;
import okhttp3.sse.EventSource;

@RequiredArgsConstructor
public class OkSseClientConnection implements SseClientConnection {

    private final EventSource eventSource;

    @Override
    public void cancel() {
        eventSource.cancel();
    }

}
