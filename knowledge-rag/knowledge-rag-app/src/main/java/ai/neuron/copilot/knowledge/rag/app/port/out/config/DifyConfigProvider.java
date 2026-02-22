package ai.neuron.copilot.knowledge.rag.app.port.out.config;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDatasetId;

public interface DifyConfigProvider {

    DifyDatasetId difyDatasetId();

    String metadataId(DatasetMetadata metadata);

}