package ai.neuron.copilot.knowledge.rag.contract.dataset;

import ai.neuron.copilot.knowledge.rag.contract.dataset.dto.CreateDatasetCmd;
import ai.neuron.copilot.knowledge.rag.contract.dataset.dto.DatasetDTO;

public interface DatasetContract {

    DatasetDTO create(CreateDatasetCmd cmd);

}
