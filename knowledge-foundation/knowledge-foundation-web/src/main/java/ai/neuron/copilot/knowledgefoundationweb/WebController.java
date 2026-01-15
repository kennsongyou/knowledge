package ai.neuron.copilot.knowledgefoundationweb;

import ai.neuron.copilot.knowledgefoundationcore.CoreService;
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
