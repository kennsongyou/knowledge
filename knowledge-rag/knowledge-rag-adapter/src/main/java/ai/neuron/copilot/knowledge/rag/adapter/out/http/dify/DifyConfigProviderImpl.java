package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.config.DatasetMetadata;
import ai.neuron.copilot.knowledge.rag.app.port.out.config.DifyConfigProvider;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDatasetId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DifyConfigProviderImpl implements DifyConfigProvider {

    private final DifyProperties difyProperties;

    @Override
    public DifyDatasetId difyDatasetId() {
        return DifyDatasetId.reconstitute(difyProperties.getDatasetId());
    }

}
