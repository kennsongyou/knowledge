package ai.neuron.copilot.knowledge.foundation.web.interceptor;

import ai.neuron.copilot.knowledge.common.http.HeaderConstants;
import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.foundation.core.context.*;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.UserId;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class ContextFilter extends OncePerRequestFilter {

	private static final ContextFilter INSTANCE = new ContextFilter();

	public static ContextFilter getInstance() {
		return INSTANCE;
	}

	@Override
	protected void doFilterInternal(
			@Nonnull HttpServletRequest request,
			@Nonnull HttpServletResponse response,
			FilterChain filterChain
	) throws ServletException, IOException {

		try {
			ContextContainer container = buildContext(request);
			ContextHolder.setContextContainer(container);
			filterChain.doFilter(request, response);
		} finally {
			ContextHolder.clear();
		}
	}

	private ContextContainer buildContext(HttpServletRequest request) {
		UserContext userContext = buildUserContext(request);
		TenantContext tenantContext = buildTenantContext(request);
		RequestContext requestContext = buildRequestContext(request);
		return new ContextContainer(requestContext, userContext, tenantContext);
	}

	private UserContext buildUserContext(HttpServletRequest request) {
		String idStr = request.getHeader(HeaderConstants.USER_ID_HEADER);
		UserId id = Optional.ofNullable(idStr).map(NumberUtils::toLong).map(UserId::reconstitute).orElse(null);
		String userName = request.getHeader(HeaderConstants.USER_NAME_HEADER);
		return new UserContext(id, userName);
	}

	private TenantContext buildTenantContext(HttpServletRequest request) {
		String idStr = request.getHeader(HeaderConstants.TENANT_ID_HEADER);
		Long id = Optional.ofNullable(idStr).map(NumberUtils::toLong).orElse(null);
		String code = request.getHeader(HeaderConstants.TENANT_CODE_HEADER);
		String name = request.getHeader(HeaderConstants.TENANT_NAME_HEADER);
		return new TenantContext(id, code, name);
	}

	private RequestContext buildRequestContext(HttpServletRequest request) {
		String requestId = request.getHeader(HeaderConstants.REQUEST_ID_HEADER);
		if (StringUtils.isBlank(requestId)) {
			requestId = IdUtils.uuidV7Str();
		}

		String traceId = request.getHeader(HeaderConstants.TRACE_ID_HEADER);
		if (StringUtils.isBlank(traceId)) {
			traceId = IdUtils.uuidV7Str();
		}

		String clientIp = getClientIp(request);
		String userAgent = request.getHeader("User-Agent");

		return new RequestContext(
				requestId,
				traceId,
				clientIp,
				userAgent,
				System.currentTimeMillis()
		);
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
