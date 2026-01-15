package ai.neuron.copilot.knowledgefoundationcore;

import ai.neuron.copilot.knowledge.common.CommonUtils;
import org.springframework.stereotype.Service;

@Service
public class CoreService {

    public String getMessage() {
        return CommonUtils.hello() + " - from Core";
    }
}
