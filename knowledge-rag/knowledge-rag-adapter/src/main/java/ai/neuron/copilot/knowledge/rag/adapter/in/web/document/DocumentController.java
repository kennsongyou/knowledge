package ai.neuron.copilot.knowledge.rag.adapter.in.web.document;

import ai.neuron.copilot.knowledge.foundation.core.exception.BusinessException;
import ai.neuron.copilot.knowledge.foundation.core.exception.FoundationCoreErrorCode;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentByFileUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final CreateDocumentByFileUseCase createDocumentByFileUseCase;

    @GetMapping
    public void create() {
        throw new BusinessException(FoundationCoreErrorCode.INTERNAL_ERROR, "ass");
    }

//    @PostMapping
//    public CreateKnowledgeBaseResponse create(@RequestBody CreateDocumentByFileCommand command) {
//        KnowledgeBaseId knowledgeBaseId = createDocumentByFileUseCase.execute(command);
//        return new CreateKnowledgeBaseResponse(knowledgeBaseId.value());
//    }

}
