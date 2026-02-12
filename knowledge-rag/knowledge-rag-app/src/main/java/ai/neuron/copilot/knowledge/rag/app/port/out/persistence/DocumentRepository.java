package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;

public interface DocumentRepository {

    void save(Document document);

    Document fetch(DocumentId documentId);

    PageResult<Document> pageByKeyword(String keyword, PageQuery pageQuery);

    boolean delete(DocumentId documentId);

}
