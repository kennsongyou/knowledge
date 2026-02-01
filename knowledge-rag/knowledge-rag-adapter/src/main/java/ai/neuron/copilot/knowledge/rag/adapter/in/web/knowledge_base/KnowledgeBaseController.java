package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.CreateKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.DeleteKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.CreateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.DeleteKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/knowledge-bases")
public class KnowledgeBaseController {

    private final CreateKnowledgeBaseUseCase createKnowledgeBaseUseCase;

    private final DeleteKnowledgeBaseUseCase deleteKnowledgeBaseUseCase;

    @PostMapping
    public CreateKnowledgeBaseResponse create(@RequestBody CreateKnowledgeBaseCommand command) {
        return createKnowledgeBaseUseCase.execute(command);
    }

    @DeleteMapping("/{knowledgeBaseId}")
    public void delete(@PathVariable("knowledgeBaseId") String knowledgeBaseId) {
        DeleteKnowledgeBaseCommand command = new DeleteKnowledgeBaseCommand(knowledgeBaseId);
        deleteKnowledgeBaseUseCase.execute(command);
    }

}
