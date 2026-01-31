package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;

public interface KnowledgeBaseRepository {

    void save(KnowledgeBase knowledgeBase);

}
