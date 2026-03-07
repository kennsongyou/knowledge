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

    public SseEmitter register(String serverId) {
        if (emitters.size() >= properties.getMaxConnections()) {
            throw new IllegalStateException(
                    String.format("Max connections exceeded: %d", properties.getMaxConnections())
            );
        }

        SseEmitter emitter = new SseEmitter(properties.getTimeout().toMillis());
        emitters.put(serverId, emitter);

        emitter.onCompletion(() -> cleanup(serverId, "completion"));
        emitter.onTimeout(() -> cleanup(serverId, "timeout"));
        emitter.onError(e -> cleanup(serverId, "error"));

        log.info("SSE connection registered - serverId: {}, total: {}",
                serverId, emitters.size());

        return emitter;
    }

    public boolean send(String serverId, SseServerMessage message) {
        SseEmitter emitter = emitters.get(serverId);

        if (emitter == null) {
            log.debug("SSE connection not found - serverId: {}", serverId);
            return false;
        }

        try {
            emitter.send(message);
            return true;
        } catch (IOException e) {
            emitter.completeWithError(e);
            cleanup(serverId, "send_error");
            log.warn("SSE send error - serverId: {}", serverId);
            return false;
        } catch (Exception e) {
            cleanup(serverId, "unexpected_error");
            log.warn("SSE unexpected error - serverId: {}", serverId, e);
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

        for (String serverId : new ArrayList<>(emitters.keySet())) {
            if (send(serverId, message)) {
                successCount++;
            } else {
                failedConnections.add(serverId);
            }
        }

        if (!failedConnections.isEmpty()) {
            failedConnections.forEach(serverId -> cleanup(serverId, "broadcast_error"));
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
        List<String> allserverIds = new ArrayList<>(emitters.keySet());
        int batchSize = properties.getHeartbeatBatchSize();

        for (int i = 0; i < allserverIds.size(); i += batchSize) {
            int end = Math.min(i + batchSize, allserverIds.size());
            List<String> batch = allserverIds.subList(i, end);

            heartbeatExecutor.execute(() -> {
                for (String serverId : batch) {
                    try {
                        SseEmitter emitter = emitters.get(serverId);
                        if (emitter != null) {
                            emitter.send(heartbeatMsg);
                        }
                    } catch (Exception e) {
                        cleanup(serverId, "heartbeat_failed");
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

    private void cleanup(String serverId, String reason) {
        SseEmitter emitter = emitters.remove(serverId);
        if (emitter != null) {
            log.info("SSE connection cleaned - serverId: {}, reason: {}", serverId, reason);
        }
    }

    public boolean connectionComplete(String serverId) {
        SseEmitter emitter = emitters.get(serverId);
        if (emitter == null) {
            log.debug("Connection not found - serverId: {}", serverId);
            return false;
        }
        emitter.complete();
        log.info("Connection closed - serverId: {}", serverId);
        return true;
    }

    public boolean connectionError(String serverId, BaseException ex) {
        SseEmitter emitter = emitters.get(serverId);
        if (emitter == null) {
            log.debug("Connection not found - serverId: {}", serverId);
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
            log.debug("Failed to send error message - serverId: {}", serverId);
        }

        emitter.completeWithError(ex);
        log.error("Connection closed with error - serverId: {}", serverId, ex);
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