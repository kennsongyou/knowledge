package ai.neuron.copilot.knowledge.rag.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(scanBasePackages = "ai.neuron.copilot.knowledge.rag")
public class KnowledgeRagBootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeRagBootstrapApplication.class, args);
	}

}
