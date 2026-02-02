package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public interface KnowledgeBaseRepository {

    void create(KnowledgeBase knowledgeBase);

    PageResult<KnowledgeBase> pageByKeyword(TenantId tenantId, String keyword, PageQuery pageQuery);

    void delete(KnowledgeBaseId knowledgeBaseId);

}
