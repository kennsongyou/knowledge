package ai.neuron.copilot.knowledge.foundation.web.sse.server;

import org.springframework.scheduling.annotation.Scheduled;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SSE服务端心跳定时调度器
 */
@Slf4j
@RequiredArgsConstructor
public class SseServerHeartbeatScheduler {

    private final SseServerManager manager;

    /**
     * 定时发送心跳
     * 间隔时间从配置中读取
     */
    @Scheduled(fixedDelayString = "${app.foundation.sse.server.heartbeat-interval:2000}",
            initialDelay = 1000)
    public void scheduleHeartbeat() {
        long startTime = System.currentTimeMillis();

        try {
            manager.sendHeartbeatBatched();

            long elapsed = System.currentTimeMillis() - startTime;
            var stats = manager.getStats();

            log.trace(
                    "Heartbeat sent - Elapsed: {}ms, Connections: {}, ActiveThreads: {}/{}",
                    elapsed,
                    stats.totalConnections(),
                    stats.threadPoolActiveCount(),
                    stats.threadPoolSize()
            );
        } catch (Exception e) {
            log.error("Heartbeat error", e);
        }
    }
}