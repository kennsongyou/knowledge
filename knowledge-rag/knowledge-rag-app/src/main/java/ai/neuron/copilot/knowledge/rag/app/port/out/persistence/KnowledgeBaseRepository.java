package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public interface KnowledgeBaseRepository {

    void create(KnowledgeBase knowledgeBase);

    void delete(KnowledgeBaseId knowledgeBaseId);

}
