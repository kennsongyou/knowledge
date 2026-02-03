package ai.neuron.copilot.knowledge.foundation.webflux.logging;

import ai.neuron.copilot.knowledge.foundation.core.logging.ContextMdcIntegrator;
import jakarta.annotation.Nonnull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class MdcWebFilter implements WebFilter {

    @Nonnull
    @Override
    public Mono<Void> filter(@Nonnull ServerWebExchange exchange, @Nonnull WebFilterChain chain) {
        try {
            ContextMdcIntegrator.integrate();
            
            return chain.filter(exchange)
                    .doFinally(signalType -> ContextMdcIntegrator.clear());
        } catch (Exception e) {
            ContextMdcIntegrator.clear();
            throw e;
        }
    }
}
