package ai.neuron.copilot.knowledge.rag.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@EnableJpaRepositories(basePackages = {"ai.neuron.copilot.knowledge.rag.infra.repository"})
@EntityScan(basePackages = {"ai.neuron.copilot.knowledge.rag.infra.entity"})
public class JpaConfig {
}
