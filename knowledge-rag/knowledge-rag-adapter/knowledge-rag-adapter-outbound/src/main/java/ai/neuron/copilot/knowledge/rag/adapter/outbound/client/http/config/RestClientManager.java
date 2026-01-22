package ai.neuron.copilot.knowledge.rag.adapter.outbound.client.http.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestClientManager {

	private final ServiceClientConfig config;

	public String getBaseUrl(String serviceName) {
		ServiceClientConfig.ClientProperties properties = config.getServices().get(serviceName);
		if (properties == null) {
			throw new IllegalArgumentException("Service not found: " + serviceName);
		}
		return properties.getBaseUrl();
	}

	public String buildUrl(String serviceName, String path) {
		String baseUrl = getBaseUrl(serviceName);
		if (path.startsWith("/")) {
			return baseUrl + path;
		}
		return baseUrl + "/" + path;
	}
}