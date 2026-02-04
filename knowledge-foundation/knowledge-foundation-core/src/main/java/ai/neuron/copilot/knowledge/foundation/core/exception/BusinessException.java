package ai.neuron.copilot.knowledge.foundation.core.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private final ErrorCode errorCode;

    private final Object[] messageArgs;

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, null, EMPTY_ARGS);
    }

    public BusinessException(ErrorCode errorCode, Object... messageArgs) {
        this(errorCode, null, messageArgs);
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, cause, EMPTY_ARGS);
    }

    public BusinessException(ErrorCode errorCode, Throwable cause, Object... messageArgs) {
        super(buildMessage(errorCode), cause);
        this.errorCode = requireErrorCode(errorCode);
        this.messageArgs = messageArgs == null ? EMPTY_ARGS : messageArgs;
    }

    private static String buildMessage(ErrorCode errorCode) {
        return errorCode == null ? null : errorCode.messageKey();
    }

    private static ErrorCode requireErrorCode(ErrorCode errorCode) {
        if (errorCode == null) {
            throw new IllegalArgumentException("errorCode must not be null");
        }
        return errorCode;
    }
}

