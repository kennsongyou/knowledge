package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DocumentId;

public interface DocumentRepository {

    void create(Document document);

    PageResult<Document> pageByKeyword(TenantId tenantId, String keyword, PageQuery pageQuery);

    void delete(DocumentId documentId);

}
