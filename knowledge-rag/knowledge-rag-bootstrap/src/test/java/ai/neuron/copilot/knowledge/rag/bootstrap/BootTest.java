package ai.neuron.copilot.knowledge.rag.bootstrap;

import ai.neuron.copilot.knowledge.foundation.core.exception.BusinessException;
import ai.neuron.copilot.knowledge.foundation.core.exception.ErrorCode;
import ai.neuron.copilot.knowledge.foundation.core.exception.FoundationCoreErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;

import java.util.Locale;

@SpringBootTest
@ActiveProfiles("local")
public class BootTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    void testMessageZhCN() {
        BusinessException aa = new BusinessException(FoundationCoreErrorCode.INTERNAL_ERROR, "aa");
        String message = aa.getMessage();
        System.out.println(message);

        String msg = messageSource.getMessage(
                "error.foundation.core.internal_error",
                null,
                Locale.CHINA
        );
        System.out.println(messageSource);
        System.out.println(msg);
    }


}
