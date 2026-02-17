package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.UserId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseName;

import java.util.Optional;

public interface KnowledgeBaseRepository {

    Optional<KnowledgeBase> fetch(KnowledgeBaseId knowledgeBaseId);

    boolean save(KnowledgeBase knowledgeBase);

    Optional<KnowledgeBase> getByName(KnowledgeBaseName name);

    PageResult<KnowledgeBase> pageByKeyword(String keyword, PageQuery pageQuery);

    boolean delete(KnowledgeBaseId knowledgeBaseId);

}
