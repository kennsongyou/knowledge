package ai.neuron.copilot.knowledge.foundation.core.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    private final Object[] messageArgs;

    public BusinessException(ErrorCode errorCode, Throwable cause, Object... messageArgs) {
        super(errorCode.messageKey(), cause);
        this.errorCode = errorCode;
        this.messageArgs = messageArgs;
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, null, (Object[]) null);
    }

    public BusinessException(ErrorCode errorCode, Object... messageArgs) {
        this(errorCode, null, messageArgs);
    }

}


