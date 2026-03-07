package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

@RequiredArgsConstructor
public class OkEventSourceListener<C extends SseClientContext>
        extends EventSourceListener {

    private final SseClientListener<C> listener;

    private final C context;

    @Override
    public void onOpen(@Nonnull EventSource eventSource,
                       @Nonnull Response response) {
        listener.onOpen(context);
    }

    @Override
    public void onEvent(@Nonnull EventSource eventSource,
                        String id,
                        String type,
                        @Nonnull String data) {

        SseEvent event = new SseEvent(id, type, data, null);
        listener.onMessage(context, event);
    }

    @Override
    public void onClosed(@Nonnull EventSource eventSource) {
        listener.onClosed(context);
    }

    @Override
    public void onFailure(@Nonnull EventSource eventSource,
                          Throwable t,
                          Response response) {
        listener.onError(context, t);
    }

}