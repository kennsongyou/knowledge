package ai.neuron.copilot.knowledge.foundation.core.context.domain.model;

import java.util.Objects;

public record UserId(Long value) {

    public UserId {
        Objects.requireNonNull(value, "User id cannot be null");
    }

    public static UserId reconstitute(Long value) {
        return new UserId(value);
    }

}
