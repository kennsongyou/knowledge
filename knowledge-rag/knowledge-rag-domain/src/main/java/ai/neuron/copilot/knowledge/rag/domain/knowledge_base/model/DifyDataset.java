package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public record DifyDataset(DifyDatasetId difyDatasetId) {

}
