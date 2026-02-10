package ai.neuron.copilot.knowledge.foundation.blob.qcloud.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties({QcloudProperties.class, QcloudCosProperties.class})
@ConditionalOnProperty(
        prefix = "app.foundation.qcloud.cos",
        name = "enabled",
        havingValue = "true"
)
public class QcloudCosConfig {

    @Bean
    @ConditionalOnMissingBean
    public COSClient cosClient(QcloudProperties qcloudProperties) {
        COSCredentials cred = new BasicCOSCredentials(qcloudProperties.getSecretId(), qcloudProperties.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(qcloudProperties.getRegion()));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public QcloudCOSClient qcloudCOSClient(COSClient cosClient, QcloudCosProperties qcloudCosProperties) {
        return new QcloudCOSClient(cosClient, qcloudCosProperties);
    }

}
