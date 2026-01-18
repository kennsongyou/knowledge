package ai.neuron.copilot.knowledge.foundation.web.config;

import ai.neuron.copilot.knowledge.foundation.web.interceptor.ContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final ContextInterceptor contextInterceptor;

	public WebMvcConfig(ContextInterceptor contextInterceptor) {
		this.contextInterceptor = contextInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(contextInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/actuator/**", "/error");
	}
}
