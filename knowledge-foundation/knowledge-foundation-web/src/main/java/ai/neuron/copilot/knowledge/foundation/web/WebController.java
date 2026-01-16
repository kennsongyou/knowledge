package ai.neuron.copilot.knowledge.foundation.web;

import ai.neuron.copilot.knowledge.foundation.core.CoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private final CoreService coreService;

    public WebController(CoreService coreService) {
        this.coreService = coreService;
    }

    @GetMapping("/")
    public String home() {
        return coreService.getMessage() + " - from Web";
    }
}
