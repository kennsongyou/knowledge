package ai.neuron.copilot.knowledge.foundation.core.exception;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException() {
        super(FoundationCoreErrorCode.RESOURCE_NOT_FOUND);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(FoundationCoreErrorCode.RESOURCE_NOT_FOUND, cause);
    }

}
