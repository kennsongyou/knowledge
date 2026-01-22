package ai.neuron.copilot.knowledge.rag.adapter.inbound.web.dataset;

import ai.neuron.copilot.knowledge.rag.contract.dataset.DatasetContract;
import ai.neuron.copilot.knowledge.rag.contract.dataset.dto.CreateDatasetCmd;
import ai.neuron.copilot.knowledge.rag.contract.dataset.dto.DatasetDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/datasets")
public class DatasetAdapter {

    private final DatasetContract contract;

    @PostMapping
    public DatasetDTO create(@RequestBody CreateDatasetCmd cmd) {
        return contract.create(cmd);
    }

}
