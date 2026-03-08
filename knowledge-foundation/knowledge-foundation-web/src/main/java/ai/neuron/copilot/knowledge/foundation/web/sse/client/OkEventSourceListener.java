package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

@Slf4j
@RequiredArgsConstructor
public class OkEventSourceListener<C extends SseClientContext>
        extends EventSourceListener {

    private final SseClientListener<C> listener;

    private final C context;

    @Override
    public void onOpen(@Nonnull EventSource eventSource,
                       @Nonnull Response response) {
        log.debug("onOpen {}", response);
        listener.onOpen(context);
    }

    @Override
    public void onEvent(@Nonnull EventSource eventSource,
                        String id,
                        String type,
                        @Nonnull String data) {
        SseEvent event = new SseEvent(id, type, data, null);
        log.debug("onEvent {}", event);
        listener.onMessage(context, event);
    }

    @Override
    public void onClosed(@Nonnull EventSource eventSource) {
        log.debug("onClosed {}", eventSource);
        listener.onClosed(context);
    }

    @Override
    public void onFailure(@Nonnull EventSource eventSource,
                          Throwable t,
                          Response response) {
        log.debug("onFailure {}, {}, {}", eventSource, t, response);
        listener.onError(context, t);
    }

}