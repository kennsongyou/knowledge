package ai.neuron.copilot.knowledge.foundation.web.sse.server;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.foundation.core.exception.BaseException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
public class SseServerManager {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    private final SseServerProperties properties;

    private final ExecutorService heartbeatExecutor;

    private final MessageSource messageSource;

    public SseServerManager(SseServerProperties properties, MessageSource messageSource) {
        this.properties = properties;
        this.messageSource = messageSource;
        int cpuCount = Runtime.getRuntime().availableProcessors();
        int coreThreads = Math.max(1, cpuCount);
        int maxThreads = Math.max(coreThreads + 1, cpuCount * 2);

        log.info("SSE Server initialized - CPU: {}, CoreThreads : {}, MaxThreads: {}",
                cpuCount, coreThreads, maxThreads);

        this.heartbeatExecutor = new ThreadPoolExecutor(
                coreThreads,
                maxThreads,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10000),
                r -> {
                    Thread t = new Thread(r, "SSE-Server-Heartbeat-" + IdUtils.uuidV4Str());
                    t.setDaemon(true);
                    return t;
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public SseEmitter register(String connectionId) {
        if (emitters.size() >= properties.getMaxConnections()) {
            throw new IllegalStateException(
                    String.format("Max connections exceeded: %d", properties.getMaxConnections())
            );
        }

        SseEmitter emitter = new SseEmitter(properties.getTimeout().toMillis());
        emitters.put(connectionId, emitter);

        emitter.onCompletion(() -> cleanup(connectionId, "completion"));
        emitter.onTimeout(() -> cleanup(connectionId, "timeout"));
        emitter.onError(e -> cleanup(connectionId, "error"));

        log.info("SSE connection registered - connectionId: {}, total: {}",
                connectionId, emitters.size());

        return emitter;
    }

    public boolean send(String connectionId, SseServerMessage message) {
        SseEmitter emitter = emitters.get(connectionId);

        if (emitter == null) {
            log.debug("SSE connection not found - connectionId: {}", connectionId);
            return false;
        }

        try {
            emitter.send(message);
            return true;
        } catch (IOException e) {
            emitter.completeWithError(e);
            cleanup(connectionId, "send_error");
            log.warn("SSE send error - connectionId: {}", connectionId);
            return false;
        } catch (Exception e) {
            cleanup(connectionId, "unexpected_error");
            log.warn("SSE unexpected error - connectionId: {}", connectionId, e);
            return false;
        }
    }

    public int broadcastToAll(SseServerMessage message) {
        if (emitters.isEmpty()) {
            log.debug("No active connections to broadcast");
            return 0;
        }

        int successCount = 0;
        List<String> failedConnections = new ArrayList<>();

        for (String connectionId : new ArrayList<>(emitters.keySet())) {
            if (send(connectionId, message)) {
                successCount++;
            } else {
                failedConnections.add(connectionId);
            }
        }

        if (!failedConnections.isEmpty()) {
            failedConnections.forEach(connectionId -> cleanup(connectionId, "broadcast_error"));
        }

        log.debug("Broadcast sent - total: {}, success: {}, failed: {}",
                emitters.size(), successCount, failedConnections.size());
        return successCount;
    }

    public void sendHeartbeatBatched() {
        if (emitters.isEmpty()) {
            return;
        }

        SseServerMessage heartbeatMsg = SseServerMessage.heartbeat();
        List<String> allConnectionIds = new ArrayList<>(emitters.keySet());
        int batchSize = properties.getHeartbeatBatchSize();

        for (int i = 0; i < allConnectionIds.size(); i += batchSize) {
            int end = Math.min(i + batchSize, allConnectionIds.size());
            List<String> batch = allConnectionIds.subList(i, end);

            heartbeatExecutor.execute(() -> {
                for (String connectionId : batch) {
                    try {
                        SseEmitter emitter = emitters.get(connectionId);
                        if (emitter != null) {
                            emitter.send(heartbeatMsg);
                        }
                    } catch (Exception e) {
                        cleanup(connectionId, "heartbeat_failed");
                    }
                }
            });
        }
    }

    public SseServerStats getStats() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) heartbeatExecutor;
        return new SseServerStats(
                emitters.size(),
                executor.getPoolSize(),
                executor.getCorePoolSize(),
                executor.getMaximumPoolSize(),
                executor.getActiveCount(),
                executor.getQueue().size()
        );
    }

    private void cleanup(String connectionId, String reason) {
        SseEmitter emitter = emitters.remove(connectionId);
        if (emitter != null) {
            log.info("SSE connection cleaned - connectionId: {}, reason: {}", connectionId, reason);
        }
    }

    public boolean connectionComplete(String connectionId) {
        SseEmitter emitter = emitters.get(connectionId);
        if (emitter == null) {
            log.debug("Connection not found - connectionId: {}", connectionId);
            return false;
        }
        emitter.complete();
        log.info("Connection closed - connectionId: {}", connectionId);
        return true;
    }

    public boolean connectionError(String connectionId, BaseException ex) {
        SseEmitter emitter = emitters.get(connectionId);
        if (emitter == null) {
            log.debug("Connection not found - connectionId: {}", connectionId);
            return false;
        }
        Locale locale = LocaleContextHolder.getLocale();
        String detail = messageSource.getMessage(ex.getErrorCode().messageKey(), ex.getMessageArgs(), locale);

        try {
            SseServerMessage errorMsg = SseServerMessage.error(
                    "system.error",
                    ex.getErrorCode().code(),
                    detail
            );
            emitter.send(errorMsg);
        } catch (IOException e) {
            log.debug("Failed to send error message - connectionId: {}", connectionId);
        }

        emitter.completeWithError(ex);
        log.error("Connection closed with error - connectionId: {}", connectionId, ex);
        return true;
    }


    public void shutdown() {
        heartbeatExecutor.shutdown();
        try {
            if (!heartbeatExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                heartbeatExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            heartbeatExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        emitters.clear();
        log.info("SseServerEmitterRegistry shutdown");
    }
}