package ai.neuron.copilot.knowledge.foundation.core.context;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.UserId;

public record UserContext (
		UserId id,
		String name
) {}
