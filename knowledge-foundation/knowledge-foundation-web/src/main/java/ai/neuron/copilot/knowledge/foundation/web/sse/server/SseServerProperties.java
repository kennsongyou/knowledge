package ai.neuron.copilot.knowledge.foundation.web.sse.server;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * SSE服务端配置
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "app.foundation.sse.server")
public class SseServerProperties {

    /**
     * 是否启用SSE服务
     */
    Boolean enabled = true;

    /**
     * 全局最大连接数
     */
    int maxConnections = 100000;

    /**
     * 连接超时时间
     */
    Duration timeout = Duration.ofMinutes(30);

    /**
     * 心跳间隔
     */
//    Duration heartbeatInterval;

    /**
     * 心跳分批发送的批次大小
     */
    int heartbeatBatchSize = 500;
}