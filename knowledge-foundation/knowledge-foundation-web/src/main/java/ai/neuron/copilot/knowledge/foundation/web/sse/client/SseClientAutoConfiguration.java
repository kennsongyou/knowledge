package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(SseClientProperties.class)
@ConditionalOnProperty(
        prefix = "app.foundation.sse.client",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class SseClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "okSseHttpClient")
    public OkHttpClient okSseHttpClient(SseClientProperties properties) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(properties.getMaxConnTotal());
        dispatcher.setMaxRequestsPerHost(properties.getMaxConnPerRoute());
        return new OkHttpClient.Builder()
                .connectTimeout(properties.getConnectTimeout())
                .readTimeout(properties.getReadTimeout())
                .dispatcher(dispatcher)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "okEventSourceFactory")
    public EventSource.Factory okEventSourceFactory(@Qualifier("okSseHttpClient") OkHttpClient okHttpClient) {
        return EventSources.createFactory(okHttpClient);
    }

    @Bean
    @ConditionalOnMissingBean(name = "okSseClientManager")
    public SseClientManager okSseClientManager(@Qualifier("okEventSourceFactory") EventSource.Factory factory) {
        return new OkSseClientManager(factory);
    }

}
