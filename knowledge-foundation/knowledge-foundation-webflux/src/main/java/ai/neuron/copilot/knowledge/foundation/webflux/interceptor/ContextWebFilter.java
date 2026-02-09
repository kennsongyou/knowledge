package ai.neuron.copilot.knowledge.foundation.webflux.interceptor;

import ai.neuron.copilot.knowledge.common.http.HeaderConstants;
import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.foundation.core.context.ContextContainer;
import ai.neuron.copilot.knowledge.foundation.core.context.RequestContext;
import ai.neuron.copilot.knowledge.foundation.core.context.TenantContext;
import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Optional;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContextWebFilter implements WebFilter {

    @Nonnull
    @Override
    public Mono<Void> filter(@Nonnull ServerWebExchange exchange, WebFilterChain chain) {
        ContextContainer container = buildContext(exchange);

        return chain.filter(exchange)
                .contextWrite(ctx -> ctx.put(ContextContainer.class, container));
    }

    private ContextContainer buildContext(ServerWebExchange exchange) {
        UserContext userContext = buildUserContext(exchange);
        TenantContext tenantContext = buildTenantContext(exchange);
        RequestContext requestContext = buildRequestContext(exchange);
        return new ContextContainer(requestContext, userContext, tenantContext);
    }

    private UserContext buildUserContext(ServerWebExchange exchange) {
        String idStr = exchange.getRequest().getHeaders().getFirst(HeaderConstants.USER_ID_HEADER);
        Long id = Optional.ofNullable(idStr).map(NumberUtils::toLong).orElse(null);
        String userName = exchange.getRequest().getHeaders().getFirst(HeaderConstants.USER_NAME_HEADER);
        return new UserContext(id, userName);
    }

    private TenantContext buildTenantContext(ServerWebExchange exchange) {
        String idStr = exchange.getRequest().getHeaders().getFirst(HeaderConstants.TENANT_ID_HEADER);
        Long id = Optional.ofNullable(idStr).map(NumberUtils::toLong).orElse(null);
        String code = exchange.getRequest().getHeaders().getFirst(HeaderConstants.TENANT_CODE_HEADER);
        String name = exchange.getRequest().getHeaders().getFirst(HeaderConstants.TENANT_NAME_HEADER);
        return new TenantContext(id, code, name);
    }

    private RequestContext buildRequestContext(ServerWebExchange exchange) {
        String requestId = exchange.getRequest().getHeaders().getFirst(HeaderConstants.REQUEST_ID_HEADER);
        if (StringUtils.isBlank(requestId)) {
            requestId = IdUtils.uuidV7Str();
        }

        String traceId = exchange.getRequest().getHeaders().getFirst(HeaderConstants.TRACE_ID_HEADER);
        if (StringUtils.isBlank(traceId)) {
            traceId = IdUtils.uuidV7Str();
        }

        String clientIp = getClientIp(exchange);
        String userAgent = exchange.getRequest().getHeaders().getFirst("User-Agent");

        return new RequestContext(
                requestId,
                traceId,
                clientIp,
                userAgent,
                System.currentTimeMillis()
        );
    }

    private String getClientIp(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String ip = request.getHeaders().getFirst("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("X-Real-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = Optional.ofNullable(request.getRemoteAddress()).map(InetSocketAddress::getAddress)
                    .map(InetAddress::getHostAddress).orElse(null);
        }
        if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}
