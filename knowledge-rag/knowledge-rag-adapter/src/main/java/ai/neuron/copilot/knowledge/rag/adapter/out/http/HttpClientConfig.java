package ai.neuron.copilot.knowledge.rag.adapter.out.http;

import jakarta.annotation.PreDestroy;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManager;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManagerBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    private CloseableHttpAsyncClient client;

    @Bean(destroyMethod = "")
    public CloseableHttpAsyncClient asyncClient() {

        PoolingAsyncClientConnectionManager cm =
                PoolingAsyncClientConnectionManagerBuilder.create()
                        .setMaxConnTotal(1000)
                        .setMaxConnPerRoute(500)
                        .build();

        client = HttpAsyncClients.custom()
                .setConnectionManager(cm)
                .evictExpiredConnections()
                .build();

        client.start();
        return client;
    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        if (client != null) {
            client.initiateShutdown();
            client.awaitShutdown(TimeValue.ofSeconds(60));
        }
    }
}