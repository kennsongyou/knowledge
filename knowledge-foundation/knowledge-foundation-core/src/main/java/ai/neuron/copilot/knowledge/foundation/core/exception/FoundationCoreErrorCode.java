package ai.neuron.copilot.knowledge.foundation.core.exception;

import lombok.AllArgsConstructor;

import java.util.Locale;

@AllArgsConstructor
public enum FoundationCoreErrorCode implements ErrorCode {

    INVALID_ARGUMENT("foundation.core.invalid_argument"),

    INTERNAL_ERROR("foundation.core.internal_error");

    private final String messageKey;

    @Override
    public String code() {
        return name().toLowerCase(Locale.ROOT);
    }

    @Override
    public String messageKey() {
        return messageKey;
    }

}
