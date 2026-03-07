package ai.neuron.copilot.knowledge.foundation.web.sse.server;

public record SseServerStats(
        int totalConnections,
        int threadPoolSize,
        int threadPoolCoreSize,
        int threadPoolMaxSize,
        int threadPoolActiveCount,
        int threadPoolQueueSize
) {}