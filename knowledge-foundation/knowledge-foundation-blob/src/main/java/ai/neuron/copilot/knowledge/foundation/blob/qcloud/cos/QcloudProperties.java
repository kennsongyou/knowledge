package ai.neuron.copilot.knowledge.foundation.blob.qcloud.cos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "app.foundation.qcloud.credential")
public class QcloudProperties {

    String appId;

    String secretId;

    String secretKey;

    String region;

}
