package ai.neuron.copilot.knowledge.rag.adapter.in.web.document;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.foundation.web.file.FileUploadAdapter;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.request.PageDocumentRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.response.CreateDocumentResponse;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.response.GetDocumentUrlResponse;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.shared.DocumentDTO;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.DeleteDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.GetDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.PageDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByTextCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.DeleteDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.FetchDocumentUrlQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.PageDocumentQuery;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final CreateDocumentUseCase createDocumentUseCase;

    private final GetDocumentUseCase getDocumentUseCase;

    private final PageDocumentUseCase pageDocumentUseCase;

    private final DeleteDocumentUseCase deleteDocumentUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDocumentResponse createByFile(@RequestPart("file") MultipartFile file,
                                               @RequestPart(value = "payload", required = false) String payload)
            throws IOException {
        FileUploadDTO fileUploadDTO = FileUploadAdapter.from(file);
        try (InputStream inputStream = file.getInputStream()) {
            CreateDocumentByFileCommand command = new CreateDocumentByFileCommand(
                    inputStream,
                    fileUploadDTO,
                    payload);
            DocumentId documentId = createDocumentUseCase.byFile(command);
            return new CreateDocumentResponse(documentId.value());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateDocumentResponse createByText(@RequestBody CreateDocumentByTextCommand command) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{document_id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentDTO get(@PathVariable("document_id") String documentId) {
        GetDocumentQuery query = new GetDocumentQuery(DocumentId.reconstitute(documentId));
        Document document = getDocumentUseCase.execute(query);
        return new DocumentDTO(
                document.getId().value(),
                document.getDisplayName(),
                document.getExtension()
        );
    }

    @GetMapping("/{document_id}/access-url")
    @ResponseStatus(HttpStatus.OK)
    public GetDocumentUrlResponse getUrl(@PathVariable("document_id") String documentId) {
        FetchDocumentUrlQuery query = new FetchDocumentUrlQuery(DocumentId.reconstitute(documentId));
        return new GetDocumentUrlResponse(getDocumentUseCase.accessUrl(query));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResult<DocumentDTO> page(@ModelAttribute PageDocumentRequest request) {
        PageDocumentQuery query = new PageDocumentQuery(new PageQuery(request.getPageNo(), request.getPageSize()), request.getKeyword());
        PageResult<Document> pageResult = pageDocumentUseCase.execute(query);
        List<DocumentDTO> records = pageResult.records().stream().map(document -> new DocumentDTO(
                document.getId().value(),
                document.getDisplayName(),
                document.getExtension()
        )).toList();
        return new PageResult<>(records, pageResult.total(), pageResult.pageNo(), pageResult.pageSize());
    }

    @DeleteMapping("/{document_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("document_id") String documentId) {
        deleteDocumentUseCase.execute(new DeleteDocumentCommand(DocumentId.reconstitute(documentId)));
    }

}
