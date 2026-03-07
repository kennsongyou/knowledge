package ai.neuron.copilot.knowledge.rag.bootstrap;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptEncryptorTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    void generateEncryptedValue() {
        String plainText = "password123";
        String encrypted = encryptor.encrypt(plainText);
        System.out.println("ENC(" + encrypted + ")");
    }
}