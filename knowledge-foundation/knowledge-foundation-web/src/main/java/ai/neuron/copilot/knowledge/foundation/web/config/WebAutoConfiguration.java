package ai.neuron.copilot.knowledge.foundation.web.config;

import ai.neuron.copilot.knowledge.foundation.web.interceptor.ContextInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class WebAutoConfiguration {

    @Bean
    public ContextInterceptor contextInterceptor() {
        return new ContextInterceptor();
    }

    @Bean
    public WebMvcConfig webMvcConfig(ContextInterceptor contextInterceptor) {
        return new WebMvcConfig(contextInterceptor);
    }

}
