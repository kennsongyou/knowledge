package ai.neuron.copilot.knowledge.foundation.web.external;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public final class InvocationContext {

    private final String target;

    private final String traceId;

    private final Map<String, String> attributes = new HashMap<>();

    public InvocationContext(String target, String traceId) {
        this.target = target;
        this.traceId = traceId;
    }

}

