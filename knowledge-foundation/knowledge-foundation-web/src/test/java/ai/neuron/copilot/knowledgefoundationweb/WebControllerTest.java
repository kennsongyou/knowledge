package ai.neuron.copilot.knowledgefoundationweb;

import ai.neuron.copilot.knowledgefoundationcore.CoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CoreService coreService;

    @Test
    public void testWebController() {
        assertNotNull(restTemplate);
        assertNotNull(coreService);
        String response = restTemplate.getForObject("http://localhost:" + port + "/", String.class);
        assertNotNull(response);
        System.out.println("Response: " + response);
    }
}
