package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "app.foundation.sse.client")
public class SseClientProperties {

    Boolean enabled = true;

    Duration connectTimeout = Duration.ofSeconds(20);

    Duration readTimeout = Duration.ofMinutes(30);

    Duration connectionRequestTimeout = Duration.ofSeconds(5);

    Duration idleConnectionEvictTime = Duration.ofSeconds(30);

    Integer maxConnTotal = 2000;

    Integer maxConnPerRoute = 1000;

}
