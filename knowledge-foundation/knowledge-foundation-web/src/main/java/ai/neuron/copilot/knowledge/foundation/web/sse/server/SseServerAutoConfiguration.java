package ai.neuron.copilot.knowledge.foundation.web.sse.server;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SSE服务端自动配置
 */
@AutoConfiguration
@EnableConfigurationProperties(SseServerProperties.class)
@ConditionalOnProperty(
        prefix = "app.foundation.sse.server",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class SseServerAutoConfiguration {

    /**
     * SSE服务端连接注册表Bean
     */
    @Bean
    public SseServerManager sseServerEmitterRegistry(SseServerProperties properties, MessageSource messageSource) {
        return new SseServerManager(properties, messageSource);
    }

    @Bean
    public SseServerHeartbeatScheduler sseServerHeartbeatScheduler(SseServerManager registry) {
        return new SseServerHeartbeatScheduler(registry);
    }

    @Configuration(proxyBeanMethods = false)
    @EnableScheduling
    static class SchedulingConfiguration {
    }

}