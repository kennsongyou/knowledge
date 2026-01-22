package ai.neuron.copilot.knowledge.rag.bootstrap;

import ai.neuron.copilot.knowledge.rag.adapter.inbound.web.dataset.DatasetAdapter;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class BootTest {

    @Resource
    private DatasetAdapter datasetAdapter;

    @Test
    void t() {
        System.out.println(datasetAdapter);
    }


}
