package ai.neuron.copilot.knowledgefoundationweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ai.neuron.copilot")
public class KnowledgeFoundationWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeFoundationWebApplication.class, args);
    }
}
