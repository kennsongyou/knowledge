package ai.neuron.copilot.knowledge.rag.adapter.in.web.document;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.TenantContext;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.web.file.FileUploadAdapter;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DocumentId;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final CreateDocumentUseCase createDocumentUseCase;

    @GetMapping
    public void page() {
        throw new UnsupportedOperationException();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CreateKnowledgeBaseResponse createByFile(@RequestPart("file") MultipartFile file,
                                                    @RequestPart(value = "payload", required = false) String payload)
            throws IOException {
        FileUploadDTO fileUploadDTO = FileUploadAdapter.from(file);
        try (InputStream inputStream = file.getInputStream()) {
            CreateDocumentByFileCommand command = new CreateDocumentByFileCommand(
                    inputStream,
                    fileUploadDTO,
                    payload,
                    TenantId.reconstitute(ContextHolder.tenant().id())
            );
            DocumentId documentId = createDocumentUseCase.byFile(command);
            return new CreateKnowledgeBaseResponse(documentId.value());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateKnowledgeBaseResponse createByText(@RequestBody CreateDocumentByFileCommand command) {
        throw new UnsupportedOperationException();
    }

}
