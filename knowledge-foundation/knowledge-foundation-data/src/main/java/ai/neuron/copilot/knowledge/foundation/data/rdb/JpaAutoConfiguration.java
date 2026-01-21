package ai.neuron.copilot.knowledge.foundation.data.rdb;

import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import java.util.Optional;

@Configuration
@ConditionalOnClass(name = {"org.springframework.data.jpa.repository.JpaRepository"})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public AuditorAware<Long> auditorAware() {
		return () -> Optional.of(ContextHolder.user()).map(UserContext::id);
	}

}
