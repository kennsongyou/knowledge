package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query.GetKnowledgeBaseQuery;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;

public interface FetchKnowledgeBaseUseCase {

    KnowledgeBase execute(GetKnowledgeBaseQuery query);

}
