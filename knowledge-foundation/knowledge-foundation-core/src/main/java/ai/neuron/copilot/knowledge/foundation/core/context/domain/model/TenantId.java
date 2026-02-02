package ai.neuron.copilot.knowledge.foundation.core.context.domain.model;

import java.util.Objects;

public record TenantId(Long value) {
    public TenantId {
        Objects.requireNonNull(value, "TenantId cannot be null");
    }
}
