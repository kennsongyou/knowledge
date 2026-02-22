package ai.neuron.copilot.knowledge.rag.app.usecase.knowledge_base_document;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.PageKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query.PageKnowledgeBaseDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseDocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class PageKnowledgeBaseDocumentUseCaseImpl implements PageKnowledgeBaseDocumentUseCase {

    private final KnowledgeBaseDocumentRepository knowledgeBaseDocumentRepository;

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    private final DocumentRepository documentRepository;

    @Override
    public PageResult<Document> execute(PageKnowledgeBaseDocumentQuery query) {
        return documentRepository.pageByKnowledgeBaseIdAndKeyword(query.pageQuery(), query.knowledgeBaseId(),
                query.keyword());
    }

}
