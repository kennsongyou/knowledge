package ai.neuron.copilot.knowledge.rag.adapter.outbound.client.http.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(ServiceClientConfig.class)
@RequiredArgsConstructor
public class HttpClientConfiguration {

	private final ServiceClientConfig config;

	@Bean
	public RestClient restClient() {
		return RestClient.builder()
				.build();
	}

	@Bean
	public RestClientManager restClientManager() {
		return new RestClientManager(config);
	}
}