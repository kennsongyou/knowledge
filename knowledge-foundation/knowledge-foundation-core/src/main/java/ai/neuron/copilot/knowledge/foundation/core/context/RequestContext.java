package ai.neuron.copilot.knowledge.foundation.core.context;

public record RequestContext(
		String requestId,
		String traceId,
		String clientIp,
		String userAgent,
		Long timestamp
) {}
