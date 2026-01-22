package ai.neuron.copilot.knowledge.foundation.core.context;

public record ContextContainer(
		RequestContext requestContext,
		UserContext userContext,
		TenantContext tenantContext
) {}
