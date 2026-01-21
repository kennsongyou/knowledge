package ai.neuron.copilot.knowledge.rag.app.dataset;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.rag.contract.dataset.DatasetContract;
import ai.neuron.copilot.knowledge.rag.contract.dataset.dto.CreateDatasetCmd;
import ai.neuron.copilot.knowledge.rag.contract.dataset.dto.DatasetDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatasetApp implements DatasetContract {

	@Override
	public DatasetDTO create(CreateDatasetCmd cmd) {
        log.info("Creating dataset with name: {}", cmd.getName());
        log.debug("Dataset creation details: {}", cmd);
        
		DatasetDTO dto = new DatasetDTO();
		dto.setName(cmd.getName());
		dto.setDescription(cmd.getDescription());
		dto.setId(IdUtils.uuidString());
        
        log.info("Dataset created successfully with id: {}", dto.getId());
        
		return dto;
	}

}
