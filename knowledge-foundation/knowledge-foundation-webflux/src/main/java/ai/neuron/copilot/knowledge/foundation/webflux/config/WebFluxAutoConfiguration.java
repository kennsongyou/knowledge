package ai.neuron.copilot.knowledge.foundation.webflux.config;

import ai.neuron.copilot.knowledge.foundation.webflux.interceptor.ContextWebFilter;
import ai.neuron.copilot.knowledge.foundation.webflux.logging.MdcWebFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class WebFluxAutoConfiguration {

    @Bean
    public ContextWebFilter contextWebFilter() {
        return new ContextWebFilter();
    }

    @Bean
    public MdcWebFilter mdcWebFilter() {
        return new MdcWebFilter();
    }

}

