package ai.neuron.copilot.knowledge.rag.adapter.out.http.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
@ConfigurationProperties(prefix = "app.external.dify")
public class DifyProperties {

    String domain;

    String datasetId;

    String datasetApiKey;

}