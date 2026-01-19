package ai.neuron.copilot.knowledge.rag.bootstrap;

import ai.neuron.copilot.knowledge.foundation.web.interceptor.ContextInterceptor;
import ai.neuron.copilot.knowledge.rag.adapter.dataset.DatasetAdapter;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BootTest {

    @Resource
    private ContextInterceptor contextInterceptor;

    @Resource
    private DatasetAdapter datasetAdapter;

    @Test
    void t() {
        System.out.println(datasetAdapter);
    }


}
