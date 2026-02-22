package ai.neuron.copilot.knowledge.rag.bootstrap;

import ai.neuron.copilot.knowledge.rag.adapter.out.http.dify.DifyProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class BootTest {

    @Autowired
    DifyProperties difyProperties;

    @Test
    void testMessageZhCN() {
        System.out.println(difyProperties.getDatasetMetadata());
    }


}
