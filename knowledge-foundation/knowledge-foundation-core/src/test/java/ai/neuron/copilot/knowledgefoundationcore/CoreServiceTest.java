package ai.neuron.copilot.knowledgefoundationcore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CoreServiceTest {

    @Autowired
    private CoreService coreService;

    @Test
    public void testCoreService() {
        assertNotNull(coreService);
        System.out.println(coreService.getMessage());
    }
}
