package ai.neuron.copilot.knowledge.rag.adapter.out.http.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.external.dify")
public class DifyConfig {

    private String domain;

    private String datasetApiKey;

}