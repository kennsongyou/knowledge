package ai.neuron.copilot.knowledge.foundation.core.exception;

public class SystemException extends BaseException {

    public SystemException() {
        super(FoundationCoreErrorCode.INTERNAL_ERROR);
    }

    public SystemException(Throwable cause) {
        super(FoundationCoreErrorCode.INTERNAL_ERROR, cause);
    }

}
