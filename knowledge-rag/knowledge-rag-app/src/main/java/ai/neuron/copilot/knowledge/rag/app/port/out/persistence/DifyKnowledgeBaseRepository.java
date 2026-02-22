package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public interface DifyKnowledgeBaseRepository {

    boolean delete(KnowledgeBaseId knowledgeBaseId);

}
