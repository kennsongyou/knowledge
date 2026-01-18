package ai.neuron.copilot.knowledge.foundation.web.interceptor;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextContainer;
import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.RequestContext;
import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class ContextInterceptor implements HandlerInterceptor {

	private static final String USER_ID_HEADER = "Neuron-User-Id";
	private static final String USER_NAME_HEADER = "Neuron-User-Name";
	private static final String TENANT_CODE_HEADER = "Neuron-Tenant-Code";
	private static final String REQUEST_ID_HEADER = "Neuron-Request-Id";
	private static final String TRACE_ID_HEADER = "Neuron-Trace-Id";

	@Override
	public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
		UserContext userContext = parseUserContext(request);
		RequestContext requestContext = parseRequestContext(request);
		ContextContainer container = new ContextContainer(userContext, requestContext);
		ContextHolder.setContextContainer(container);
		return true;
	}

	@Override
	public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
								@Nonnull Object handler, Exception ex) {
		ContextHolder.clear();
	}

	private UserContext parseUserContext(HttpServletRequest request) {
		String userIdStr = request.getHeader(USER_ID_HEADER);
		String userName = request.getHeader(USER_NAME_HEADER);
		String tenantCode = request.getHeader(TENANT_CODE_HEADER);
		if (!StringUtils.isNumeric(userIdStr)) {
			return null;
		}
		Long userId = NumberUtils.toLong(userIdStr);
		return new UserContext(userId, userName, tenantCode);
	}

	private RequestContext parseRequestContext(HttpServletRequest request) {
		String requestId = request.getHeader(REQUEST_ID_HEADER);
		if (requestId == null || requestId.isEmpty()) {
			requestId = UUID.randomUUID().toString();
		}
		
		String traceId = request.getHeader(TRACE_ID_HEADER);
		if (traceId == null || traceId.isEmpty()) {
			traceId = UUID.randomUUID().toString();
		}
		
		String clientIp = getClientIp(request);
		String userAgent = request.getHeader("User-Agent");
		
		return new RequestContext(requestId, traceId, clientIp, userAgent, System.currentTimeMillis());
	}

	private String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.contains(",")) {
			ip = ip.split(",")[0].trim();
		}
		return ip;
	}
}
