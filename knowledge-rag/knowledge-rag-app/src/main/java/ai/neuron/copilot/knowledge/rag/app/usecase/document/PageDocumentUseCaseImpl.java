package ai.neuron.copilot.knowledge.rag.app.usecase.document;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.PageDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.PageDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PageDocumentUseCaseImpl implements PageDocumentUseCase {

    private final DocumentRepository documentRepository;

    @Override
    public PageResult<Document> execute(PageDocumentQuery query) {
        return documentRepository.pageByKeyword(query.pageQuery(), query.keyword());
    }

}
