package ai.neuron.copilot.knowledge.rag.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ai.neuron.copilot.knowledge")
public class KnowledgeRagBootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeRagBootstrapApplication.class, args);
	}

}
