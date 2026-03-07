package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.DifyDatasetsClient;
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

    private final DifyProperties difyProperties;

    private final RestClient.Builder restClientBuilder;

    @Bean
    public DifyDatasetsClient difyDatasetsClient(
            @Qualifier("difyDatasetsRestClient") RestClient difyDatasetsRestClient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(difyDatasetsRestClient))
                        .build();
        return factory.createClient(DifyDatasetsClientImpl.class);
    }

    @Bean
    public RestClient difyDatasetsRestClient() {

        ClientHttpRequestInterceptor requestInterceptor = (request, body, execution) -> {
            request.getHeaders().setBearerAuth(difyProperties.getDatasetApiKey());
            return execution.execute(request, body);
        };

        return restClientBuilder
                .baseUrl(difyProperties.getDomain())
                .requestInterceptor(requestInterceptor)
                .build();
    }

}