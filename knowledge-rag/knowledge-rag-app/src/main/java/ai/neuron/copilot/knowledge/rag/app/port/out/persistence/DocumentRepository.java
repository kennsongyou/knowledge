package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;

public interface DocumentRepository {

    void save(Document document);

    Document get(DocumentId documentId, TenantId tenantId);

    PageResult<Document> pageByKeyword(String keyword, PageQuery pageQuery, TenantId tenantId);

    void delete(DocumentId documentId, TenantId tenantId);

}
