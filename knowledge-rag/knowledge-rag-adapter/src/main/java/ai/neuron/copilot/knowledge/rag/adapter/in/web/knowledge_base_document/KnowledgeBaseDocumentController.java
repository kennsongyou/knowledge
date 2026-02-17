package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base_document;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.shared.DocumentDTO;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base_document.dto.request.PageKnowledgeBaseDocumentRequest;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.CreateKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.DeleteKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.ExistsKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.PageKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.CreateKnowledgeBaseDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.DeleteKnowledgeBaseDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query.ExistsKnowledgeBaseDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query.PageKnowledgeBaseDocumentQuery;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/knowledge-bases/{knowledge_base_id}/documents")
public class KnowledgeBaseDocumentController {

    private final ExistsKnowledgeBaseDocumentUseCase existsKnowledgeBaseDocumentUseCase;

    private final CreateKnowledgeBaseDocumentUseCase createKnowledgeBaseDocumentUseCase;

    private final DeleteKnowledgeBaseDocumentUseCase deleteKnowledgeBaseDocumentUseCase;

    private final PageKnowledgeBaseDocumentUseCase pageKnowledgeBaseDocumentUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResult<DocumentDTO> page(@PathVariable("knowledge_base_id") String knowledgeBaseId,
                     @ModelAttribute PageKnowledgeBaseDocumentRequest request) {

        PageKnowledgeBaseDocumentQuery query = new PageKnowledgeBaseDocumentQuery(new PageQuery(request.getPageNo(),
                request.getPageSize()), KnowledgeBaseId.reconstitute(knowledgeBaseId));
        PageResult<Document> pageResult = pageKnowledgeBaseDocumentUseCase.execute(query);
        List<DocumentDTO> records = pageResult.records().stream().map(document -> new DocumentDTO(
                document.getId().value(),
                document.getDisplayName(),
                document.getExtension()
        )).toList();
        return new PageResult<>(records, pageResult.total(), pageResult.pageNo(), pageResult.pageSize());

    }



    @GetMapping("/{document_id}")
    @ResponseStatus(HttpStatus.OK)
    public void exists(@PathVariable("knowledge_base_id") String knowledgeBaseId,
                       @PathVariable("document_id") String documentId) {
        ExistsKnowledgeBaseDocumentQuery query = new ExistsKnowledgeBaseDocumentQuery(
                KnowledgeBaseId.reconstitute(knowledgeBaseId),
                DocumentId.reconstitute(documentId)
        );
        existsKnowledgeBaseDocumentUseCase.requireRelationExists(query);
    }


    @PostMapping("/{document_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("knowledge_base_id") String knowledgeBaseId,
                                              @PathVariable("document_id") String documentId) {
        CreateKnowledgeBaseDocumentCommand command = new CreateKnowledgeBaseDocumentCommand(
                KnowledgeBaseId.reconstitute(knowledgeBaseId),
                DocumentId.reconstitute(documentId),
                TenantId.reconstitute(ContextHolder.tenant().id())
        );
        createKnowledgeBaseDocumentUseCase.execute(command);
    }

    @DeleteMapping("/{document_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("knowledge_base_id") String knowledgeBaseId,
                       @PathVariable("document_id") String documentId) {
        DeleteKnowledgeBaseDocumentCommand command = new DeleteKnowledgeBaseDocumentCommand(
                KnowledgeBaseId.reconstitute(knowledgeBaseId),
                DocumentId.reconstitute(documentId),
                TenantId.reconstitute(ContextHolder.tenant().id())
        );
        deleteKnowledgeBaseDocumentUseCase.execute(command);
    }

}
