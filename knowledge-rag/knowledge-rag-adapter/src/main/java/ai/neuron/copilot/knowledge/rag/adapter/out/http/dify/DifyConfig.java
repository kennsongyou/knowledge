package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.config.DatasetMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class DifyConfig {

    @Bean
    public Converter<String, DatasetMetadata> datasetMetadataConverter() {
        return DatasetMetadata::fromName;
    }

}
