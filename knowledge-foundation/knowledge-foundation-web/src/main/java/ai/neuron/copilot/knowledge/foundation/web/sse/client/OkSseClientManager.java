package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class OkSseClientManager implements SseClientManager {

    private final EventSource.Factory factory;

    private static final MediaType JSON = MediaType.get("application/json");

    private final Map<String, SseClient<? extends SseClientContext>> sseClients = new ConcurrentHashMap<>();

    public <C extends SseClientContext> String register(SseRequest sseRequest, C context,
                                                              SseClientListener<C> listener) {
        Request request = toRequest(sseRequest);
        OkEventSourceListener<C> okEventSourceListener = new OkEventSourceListener<>(listener, context);
        EventSource eventSource = factory.newEventSource(request, okEventSourceListener);
        OkSseClientConnection clientConnection = new OkSseClientConnection(eventSource);
        String clientId = IdUtils.uuidV4Str();
        sseClients.put(clientId, new SseClient<>(clientConnection, context, listener));
        return clientId;
    }

    public <C extends SseClientContext> void cleanup(String clientId, String reason) {
        SseClient<? extends SseClientContext> sseClient = sseClients.remove(clientId);
        if (sseClient == null) {
            log.warn("SSE client clean up failed. No matching sse client found for clientId: {}", clientId);
            return;
        }
        log.info("SSE client cleaned - clientId: {}, reason: {}", clientId, reason);
        sseClient.cancel();
    }

    private static Request toRequest(SseRequest sseRequest) {
        Request.Builder builder = new Request.Builder()
                .url(sseRequest.getUrl());
        if (sseRequest.getHeaders() != null) {
            sseRequest.getHeaders().forEach((k, v) ->
                    builder.addHeader(k, String.valueOf(v)));
        }
        Map<String, Object> defaultHeaders = Map.of(
                "Accept", "text/event-stream",
                "Cache-Control", "no-cache"
        );
        Stream.concat(
                defaultHeaders.entrySet().stream(),
                Optional.ofNullable(sseRequest.getHeaders())
                        .map(Map::entrySet)
                        .stream()
                        .flatMap(Collection::stream)
        ).forEach(e -> builder.addHeader(e.getKey(), String.valueOf(e.getValue())));
        RequestBody body = null;
        if (sseRequest.getJsonBody() != null) {
            body = RequestBody.create(sseRequest.getJsonBody(), JSON);
        }
        builder.method(sseRequest.getMethod(), body);
        return builder.build();
    }

}
