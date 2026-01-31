package ai.neuron.copilot.knowledge.foundation.core.context;

import java.util.Optional;

public final class ContextHolder {

	private static final ThreadLocal<ContextContainer> CONTEXT_CONTAINER = new ThreadLocal<>();

	public static void setContextContainer(ContextContainer container) {
		CONTEXT_CONTAINER.set(container);
	}

	public static ContextContainer getContextContainer() {
		return CONTEXT_CONTAINER.get();
	}

	public static void clear() {
		CONTEXT_CONTAINER.remove();
	}

	public static RequestContext request() {
		return Optional.ofNullable(CONTEXT_CONTAINER.get())
				.map(ContextContainer::requestContext).orElse(null);
	}

	public static UserContext user() {
		return Optional.ofNullable(CONTEXT_CONTAINER.get())
				.map(ContextContainer::userContext).orElse(null);
	}

	public static TenantContext tenant() {
		return Optional.ofNullable(CONTEXT_CONTAINER.get())
				.map(ContextContainer::tenantContext).orElse(null);
	}


}
