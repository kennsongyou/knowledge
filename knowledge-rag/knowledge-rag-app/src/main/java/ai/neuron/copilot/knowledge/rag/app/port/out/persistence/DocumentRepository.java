package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

import java.util.Optional;

public interface DocumentRepository {

    boolean save(Document document);

    Optional<Document> fetch(DocumentId documentId);

    PageResult<Document> pageByKeyword(PageQuery pageQuery, String keyword);

    boolean delete(DocumentId documentId);

    PageResult<Document> pageByKnowledgeBaseIdAndKeyword(PageQuery pageQuery, KnowledgeBaseId knowledgeBaseId,
                                                         String keyword);


}
