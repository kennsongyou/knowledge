package ai.neuron.copilot.knowledge.rag.bootstrap;

import ai.neuron.copilot.knowledge.rag.adapter.out.http.dify.DifyDatasetsClient;
import ai.neuron.copilot.knowledge.rag.adapter.out.http.dify.dto.response.PageDatasetsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class BootTest {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private DifyDatasetsClient difyDatasetsClient;

    @Autowired
    @Qualifier("snakeObjectMapper")
    private ObjectMapper objectMapper;

    @Test
    void testMessageZhCN() {
        try {
            PageDatasetsResponse dataset = difyDatasetsClient.pageDatasets("金山", null, null, null, null);
            objectMapper.writeValue(System.out, dataset);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
