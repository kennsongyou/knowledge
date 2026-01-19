package ai.neuron.copilot.knowledge.rag.app.dataset;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.rag.contract.dataset.DatasetContract;
import ai.neuron.copilot.knowledge.rag.contract.dataset.dto.CreateDatasetCmd;
import ai.neuron.copilot.knowledge.rag.contract.dataset.dto.DatasetDTO;
import org.springframework.stereotype.Service;

@Service
public class DatasetApp implements DatasetContract {

	@Override
	public DatasetDTO create(CreateDatasetCmd cmd) {
		DatasetDTO dto = new DatasetDTO();
		dto.setName(cmd.getName());
		dto.setDescription(cmd.getDescription());
		dto.setId(IdUtils.uuidString());
		return dto;
	}

}
