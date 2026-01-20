package ai.neuron.copilot.knowledge.foundation.data.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@ConditionalOnClass(name = {"org.springframework.data.jpa.repository.JpaRepository"})
@EnableJpaRepositories(basePackages = {"ai.neuron.copilot.**.repository"})
public class JpaAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
