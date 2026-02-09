package ai.neuron.copilot.knowledge.foundation.core.exception;

public class ResourceAlreadyExistException extends BaseException {

    public ResourceAlreadyExistException() {
        super(FoundationCoreErrorCode.RESOURCE_ALREADY_EXIST);
    }

    public ResourceAlreadyExistException(Throwable cause) {
        super(FoundationCoreErrorCode.RESOURCE_ALREADY_EXIST, cause);
    }

}
