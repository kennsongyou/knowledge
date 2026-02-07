package ai.neuron.copilot.knowledge.foundation.core.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    private final Object[] messageArgs;

    public BaseException(ErrorCode errorCode, Throwable cause, Object... messageArgs) {
        super(errorCode.messageKey(), cause);
        this.errorCode = errorCode;
        this.messageArgs = messageArgs;
    }

    public BaseException(ErrorCode errorCode) {
        this(errorCode, null, (Object[]) null);
    }

    public BaseException(ErrorCode errorCode, Object... messageArgs) {
        this(errorCode, null, messageArgs);
    }

}


