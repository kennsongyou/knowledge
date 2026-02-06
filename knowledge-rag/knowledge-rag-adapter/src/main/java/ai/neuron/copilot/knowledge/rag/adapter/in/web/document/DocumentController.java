package ai.neuron.copilot.knowledge.rag.adapter.in.web.document;

import ai.neuron.copilot.knowledge.common.io.InputStreamDTO;
import ai.neuron.copilot.knowledge.foundation.core.exception.BusinessException;
import ai.neuron.copilot.knowledge.foundation.core.exception.FoundationCoreErrorCode;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DocumentId;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.OptionalLong;

@AllArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final CreateDocumentUseCase createDocumentUseCase;

    @GetMapping
    public void page() {
        throw new BusinessException(FoundationCoreErrorCode.INTERNAL_ERROR, "ass");
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CreateKnowledgeBaseResponse createByFile(@RequestPart("file") MultipartFile file,
                                                    @RequestPart(value = "payload", required = false) String payload)
            throws IOException {
        InputStreamDTO inputStreamDTO = new InputStreamDTO(file.getInputStream(), OptionalLong.of(file.getSize()));
        CreateDocumentByFileCommand command = new CreateDocumentByFileCommand(payload, inputStreamDTO);
        DocumentId documentId = createDocumentUseCase.byFile(command);
        return new CreateKnowledgeBaseResponse(documentId.value());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateKnowledgeBaseResponse createByText(@RequestBody CreateDocumentByFileCommand command) {
        return null;
    }

}
