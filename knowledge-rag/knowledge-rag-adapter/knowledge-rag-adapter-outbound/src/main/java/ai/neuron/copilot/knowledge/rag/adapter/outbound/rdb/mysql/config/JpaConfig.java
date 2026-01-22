package ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.mysql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@EnableJpaRepositories(basePackages = {"ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.**.repository"})
@EntityScan(basePackages = {"ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.**.entity"})
public class JpaConfig {
}
