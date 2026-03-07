package ai.neuron.copilot.knowledge.foundation.core.exception;

public class InvalidArgumentException extends BaseException {

    public InvalidArgumentException() {
        super(FoundationCoreErrorCode.INVALID_ARGUMENT);
    }

    public InvalidArgumentException(Throwable cause) {
        super(FoundationCoreErrorCode.INVALID_ARGUMENT, cause);
    }

    public InvalidArgumentException(Throwable cause, Object... args) {
        super(FoundationCoreErrorCode.INVALID_ARGUMENT, cause, args);
    }

}
