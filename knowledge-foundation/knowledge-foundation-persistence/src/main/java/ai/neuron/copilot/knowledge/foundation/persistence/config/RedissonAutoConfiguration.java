package ai.neuron.copilot.knowledge.foundation.persistence.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(name = {"org.redisson.Redisson"})
public class RedissonAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public RedissonClient redissonClient() {
		Config config = new Config();
		SingleServerConfig serverConfig = config.useSingleServer()
				.setAddress("redis://127.0.0.1:6379")
				.setPassword("")
				.setDatabase(0);
		return Redisson.create(config);
	}
}
