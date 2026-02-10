package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base_document;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.CreateKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.DeleteKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.ExistsKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.CreateKnowledgeBaseDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.DeleteKnowledgeBaseDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query.ExistsKnowledgeBaseDocumentQuery;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/knowledge-bases/{knowledge_base_id}/documents/{document_id}")
public class KnowledgeBaseDocumentController {

    private final ExistsKnowledgeBaseDocumentUseCase existsKnowledgeBaseDocumentUseCase;

    private final CreateKnowledgeBaseDocumentUseCase createKnowledgeBaseDocumentUseCase;

    private final DeleteKnowledgeBaseDocumentUseCase deleteKnowledgeBaseDocumentUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void exists(@PathVariable("knowledge_base_id") String knowledgeBaseId,
                       @PathVariable("document_id") String documentId) {
        ExistsKnowledgeBaseDocumentQuery query = new ExistsKnowledgeBaseDocumentQuery(
                KnowledgeBaseId.reconstitute(knowledgeBaseId),
                DocumentId.reconstitute(documentId),
                TenantId.reconstitute(ContextHolder.tenant().id())
        );
        existsKnowledgeBaseDocumentUseCase.requireRelationExists(query);
    }


    @PostMapping
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

    @DeleteMapping
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
