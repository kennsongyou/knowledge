package ai.neuron.copilot.knowledge.foundation.blob.qcloud.cos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "app.foundation.qcloud.cos")
public class QcloudCosProperties {

    Boolean enabled;

    String bucket;

    String keyPrefix;

    String domain;

}
