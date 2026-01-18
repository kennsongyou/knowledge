package ai.neuron.copilot.knowledge.foundation.core.context;

public record UserContext (
		Long id,
		String name,
		String tenantCode
) {}
