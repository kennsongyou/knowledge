package ai.neuron.copilot.knowledge.foundation.core.exception;

public class ResourceNotExistException extends BaseException {

    public ResourceNotExistException() {
        super(FoundationCoreErrorCode.RESOURCE_NOT_EXIST);
    }

    public ResourceNotExistException(Throwable cause) {
        super(FoundationCoreErrorCode.RESOURCE_NOT_EXIST, cause);
    }

}
