package ai.neuron.copilot.knowledge.rag.contract.dataset.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateDatasetCmd {

    String name;

    String description;

}
