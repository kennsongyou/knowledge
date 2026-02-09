package ai.neuron.copilot.knowledge.foundation.core.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FoundationCoreErrorCode implements ErrorCode {

    INVALID_ARGUMENT("invalid_argument", "error.foundation.core.invalid_argument"),

    INTERNAL_ERROR("internal_error", "error.foundation.core.internal_error"),

    RESOURCE_NOT_FOUND("resource_not_found", "error.foundation.core.resource_not_found"),

    RESOURCE_ALREADY_EXIST("resource_already_exist", "error.foundation.core.resource_already_exist");

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
