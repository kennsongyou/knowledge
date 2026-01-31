package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.CreateKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.CreateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/knowledge-bases")
public class KnowledgeBaseController {

    private final CreateKnowledgeBaseUseCase createKnowledgeBaseUseCase;

    @PostMapping
    public CreateKnowledgeBaseResponse create(@RequestBody CreateKnowledgeBaseCommand command) {
        return createKnowledgeBaseUseCase.execute(command);
    }

}
