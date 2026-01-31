package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"ai.neuron.copilot.knowledge.rag.adapter.out.rdb.**.jpa.repository"})
@EntityScan(basePackages = {"ai.neuron.copilot.knowledge.rag.adapter.out.rdb.**.jpa.po"})
public class JpaConfig {
}
