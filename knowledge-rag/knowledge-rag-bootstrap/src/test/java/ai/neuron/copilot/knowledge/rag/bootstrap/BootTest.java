package ai.neuron.copilot.knowledge.rag.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("local")
public class BootTest {

    @Value("${jasypt.encryptor.password}")
    private String password;

//    @Autowired
//    private MessageSource messageSource;
//
//    @Autowired
//    private DifyDatasetsClient difyDatasetsClient;
//
//    @Autowired
//    private ObjectMapper objectMapper;

    @Test
    void testMessageZhCN() {
        System.out.println(password);
//        try {
//            PageDatasetsResponse dataset = difyDatasetsClient.pageDatasets("金山", null, null, null, null);
//            objectMapper.writeValue(System.out, dataset);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


}
