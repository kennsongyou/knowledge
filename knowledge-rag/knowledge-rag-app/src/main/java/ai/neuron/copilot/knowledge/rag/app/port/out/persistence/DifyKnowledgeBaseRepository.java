package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyKnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public interface DifyKnowledgeBaseRepository {

    boolean save(DifyKnowledgeBase difyKnowledgeBase);

    boolean delete(KnowledgeBaseId knowledgeBaseId);

}
