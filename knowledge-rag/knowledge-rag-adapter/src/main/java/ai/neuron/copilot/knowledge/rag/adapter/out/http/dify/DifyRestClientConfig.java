package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.DifyDatasetsClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
    public RestClient difyDatasetsRestClient(@Qualifier("snakeObjectMapper") ObjectMapper objectMapper) {

        ClientHttpRequestInterceptor requestInterceptor = (request, body, execution) -> {
            request.getHeaders().setBearerAuth(difyProperties.getDatasetApiKey());
            return execution.execute(request, body);
        };

        return restClientBuilder
                .baseUrl(difyProperties.getDomain())
                .requestInterceptor(requestInterceptor)
                .messageConverters(converters -> converters.stream()
                        .filter(c -> c instanceof MappingJackson2HttpMessageConverter)
                        .findFirst()
                        .ifPresent(c -> ((MappingJackson2HttpMessageConverter) c)
                                .setObjectMapper(objectMapper)))
                .build();
    }

}