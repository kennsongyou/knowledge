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
        // 明文密码
        String plainText = "password123";
        String encrypted = encryptor.encrypt(plainText);
        // 加密后
        System.out.println("ENC(" + encrypted + ")");
    }
}