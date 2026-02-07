package ai.neuron.copilot.knowledge.foundation.core.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FoundationCoreErrorCode implements ErrorCode {

    INVALID_ARGUMENT("foundation_invalid_argument", "error.foundation.core.invalid_argument"),

    INTERNAL_ERROR("foundation_internal_error", "error.foundation.core.internal_error");

    private final String code;

    private final String messageKey;

    @Override
    public String code() {
        return code;
    }

    @Override
    public String messageKey() {
        return messageKey;
    }

}
