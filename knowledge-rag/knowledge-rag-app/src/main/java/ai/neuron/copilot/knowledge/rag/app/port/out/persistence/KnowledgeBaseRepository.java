package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseName;

import java.util.Optional;

public interface KnowledgeBaseRepository {

    KnowledgeBase get(KnowledgeBaseId knowledgeBaseId, TenantId tenantId);

    void save(KnowledgeBase knowledgeBase);

    Optional<KnowledgeBase> getByName(KnowledgeBaseName name, TenantId tenantId);

    PageResult<KnowledgeBase> pageByKeyword(String keyword, PageQuery pageQuery, TenantId tenantId);

    void delete(KnowledgeBaseId knowledgeBaseId, TenantId tenantId);

}
