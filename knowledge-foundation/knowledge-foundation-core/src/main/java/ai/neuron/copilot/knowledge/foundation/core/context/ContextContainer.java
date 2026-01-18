package ai.neuron.copilot.knowledge.foundation.core.context;

public record ContextContainer(
		UserContext userContext,
		RequestContext requestContext
) {}
