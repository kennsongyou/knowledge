package ai.neuron.copilot.knowledge.rag.app.port.out.config;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDatasetId;

@FunctionalInterface
public interface DifyDatasetIdProvider {

    DifyDatasetId get();

}