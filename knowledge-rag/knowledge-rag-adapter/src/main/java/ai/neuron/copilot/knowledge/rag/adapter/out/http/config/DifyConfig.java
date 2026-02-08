package ai.neuron.copilot.knowledge.rag.adapter.out.http.config;

import ai.neuron.copilot.knowledge.rag.app.port.out.config.DifyDatasetIdProvider;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDatasetId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DifyConfig {

    private final DifyProperties difyProperties;

    @Bean
    public DifyDatasetIdProvider datasetIdProvider() {
        return () -> DifyDatasetId.reconstitute(difyProperties.getDatasetId());
    }

}
