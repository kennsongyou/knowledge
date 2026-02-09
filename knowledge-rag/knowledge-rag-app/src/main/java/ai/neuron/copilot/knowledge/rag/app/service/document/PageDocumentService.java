package ai.neuron.copilot.knowledge.rag.app.service.document;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.PageDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.PageDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PageDocumentService implements PageDocumentUseCase {

    private final DocumentRepository documentRepository;

    @Override
    public PageResult<Document> execute(PageDocumentQuery query) {
        return documentRepository.pageByKeyword(query.keyword(), query.pageQuery(), query.tenantId());
    }

}
