package ai.neuron.copilot.knowledge.foundation.web.interceptor;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.foundation.core.context.*;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class ContextInterceptor implements HandlerInterceptor {

	private static final String USER_ID_HEADER = "Neuron-User-Id";
	private static final String USER_NAME_HEADER = "Neuron-User-Name";
	private static final String TENANT_ID_HEADER = "Neuron-Tenant-Id";
	private static final String TENANT_CODE_HEADER = "Neuron-Tenant-Code";
	private static final String TENANT_NAME_HEADER = "Neuron-Tenant-Name";
	private static final String REQUEST_ID_HEADER = "Neuron-Request-Id";
	private static final String TRACE_ID_HEADER = "Neuron-Trace-Id";

	@Override
	public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
		UserContext userContext = parseUserContext(request);
		RequestContext requestContext = parseRequestContext(request);
		TenantContext tenantContext = parseTenantContext(request);
		ContextContainer container = new ContextContainer(requestContext, userContext, tenantContext);
		ContextHolder.setContextContainer(container);
		return true;
	}

	@Override
	public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
								@Nonnull Object handler, Exception ex) {
		ContextHolder.clear();
	}

	private UserContext parseUserContext(HttpServletRequest request) {
		String idStr = request.getHeader(USER_ID_HEADER);
		String name = request.getHeader(USER_NAME_HEADER);
		Long id = Optional.of(idStr).map(NumberUtils::toLong).get();
		return new UserContext(id, name);
	}

	private TenantContext parseTenantContext(HttpServletRequest request) {
		String idStr = request.getHeader(TENANT_ID_HEADER);
		String code = request.getHeader(TENANT_CODE_HEADER);
		String name = request.getHeader(TENANT_NAME_HEADER);
		Long id = Optional.of(idStr).map(NumberUtils::toLong).get();
		return new TenantContext(id, code, name);
	}

	private RequestContext parseRequestContext(HttpServletRequest request) {
		String requestId = request.getHeader(REQUEST_ID_HEADER);
		if (StringUtils.isBlank(requestId)) {
			requestId = IdUtils.uuidString();
		}
		
		String traceId = request.getHeader(TRACE_ID_HEADER);
		if (StringUtils.isBlank(traceId)) {
			traceId = IdUtils.uuidString();
		}
		
		String clientIp = getClientIp(request);
		String userAgent = request.getHeader("User-Agent");
		
		return new RequestContext(requestId, traceId, clientIp, userAgent, System.currentTimeMillis());
	}

	private String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (StringUtils.isNotBlank(ip) && ip.contains(",")) {
			ip = ip.split(",")[0].trim();
		}
		return ip;
	}
}
