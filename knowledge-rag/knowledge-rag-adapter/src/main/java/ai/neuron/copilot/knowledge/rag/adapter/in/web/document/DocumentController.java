package ai.neuron.copilot.knowledge.rag.adapter.in.web.document;

import ai.neuron.copilot.knowledge.foundation.core.exception.BusinessException;
import ai.neuron.copilot.knowledge.foundation.core.exception.FoundationCoreErrorCode;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentByFileUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final CreateDocumentByFileUseCase createDocumentByFileUseCase;

    @GetMapping
    public void page() {
        throw new BusinessException(FoundationCoreErrorCode.INTERNAL_ERROR, "ass");
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CreateKnowledgeBaseResponse createByFile(@RequestPart("file") MultipartFile file,
                                              @RequestPart(value = "payload", required = false) String payload) {
//        KnowledgeBaseId knowledgeBaseId = createDocumentByFileUseCase.execute(command);
//        return new CreateKnowledgeBaseResponse(knowledgeBaseId.value());
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateKnowledgeBaseResponse createByText(@RequestBody CreateDocumentByFileCommand command) {
        return null;
    }

}
