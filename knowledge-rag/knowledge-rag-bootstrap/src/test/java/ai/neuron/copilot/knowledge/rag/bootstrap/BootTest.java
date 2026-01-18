package ai.neuron.copilot.knowledge.rag.bootstrap;

import ai.neuron.copilot.knowledge.foundation.web.interceptor.ContextInterceptor;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BootTest {

    @Resource
    private ContextInterceptor contextInterceptor;

    @Test
    void t() {
        System.out.println(contextInterceptor);
    }


}
