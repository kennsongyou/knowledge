package ai.neuron.copilot.knowledge.foundation.core.context.domain.model;

import java.util.Objects;

public record TenantId(Long value) {

    public TenantId {
        Objects.requireNonNull(value, "TenantId cannot be null");
    }

    public static TenantId reconstitute(Long value) {
        return new TenantId(value);
    }

}
