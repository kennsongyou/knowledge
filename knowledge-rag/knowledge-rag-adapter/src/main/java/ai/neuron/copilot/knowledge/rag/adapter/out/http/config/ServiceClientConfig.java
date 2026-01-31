package ai.neuron.copilot.knowledge.rag.adapter.out.http.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "service.clients")
public class ServiceClientConfig {
	private Map<String, ClientProperties> services;

	@Data
	public static class ClientProperties {
		private String baseUrl;
		private Integer connectTimeout = 5000;
		private Integer readTimeout = 30000;
	}
}