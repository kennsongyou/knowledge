package ai.neuron.copilot.knowledge.foundation.web.config;

import ai.neuron.copilot.knowledge.foundation.web.exception.DefaultGlobalExceptionHandler;
import ai.neuron.copilot.knowledge.foundation.web.interceptor.ContextFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WebAutoConfiguration {

    @Bean
    public FilterRegistrationBean<ContextFilter> contextFilterRegistration() {
        FilterRegistrationBean<ContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(ContextFilter.getInstance());
        registration.addUrlPatterns("/*");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }


    @Bean
    @ConditionalOnMissingBean(RestControllerAdvice.class)
    public DefaultGlobalExceptionHandler defaultRestControllerAdvice(MessageSource messageSource) {
        return new DefaultGlobalExceptionHandler(messageSource);
    }

}
