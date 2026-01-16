package ai.neuron.copilot.knowledge.foundation.core;

import ai.neuron.copilot.knowledge.common.CommonUtils;
import org.springframework.stereotype.Service;

@Service
public class CoreService {

    public String getMessage() {
        return CommonUtils.hello() + " - from Core";
    }
}
