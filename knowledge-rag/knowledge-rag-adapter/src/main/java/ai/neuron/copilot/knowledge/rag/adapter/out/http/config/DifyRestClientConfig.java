package ai.neuron.copilot.knowledge.rag.adapter.out.http.config;

import ai.neuron.copilot.knowledge.rag.adapter.out.http.dify.DifyDatasetsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@RequiredArgsConstructor
@Configuration
public class DifyRestClientConfig {

    private final DifyConfig difyConfig;

    @Bean
    public DifyDatasetsClient difyKnowledgeClient(
            @Qualifier("difyDatasetClient") RestClient difyRestClient) {

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(difyRestClient))
                        .build();

        return factory.createClient(DifyDatasetsClient.class);
    }

    @Bean
    public RestClient difyDatasetClient(RestClient.Builder builder) {
        return builder
                .baseUrl(difyConfig.getDomain())
                .requestInterceptor(difyDatasetHeaderInterceptor())
                .build();
    }

    @Bean
    public ClientHttpRequestInterceptor difyDatasetHeaderInterceptor() {
        return (request, body, execution) -> {
            request.getHeaders().setBearerAuth(difyConfig.getDatasetApiKey());
            return execution.execute(request, body);
        };
    }

}