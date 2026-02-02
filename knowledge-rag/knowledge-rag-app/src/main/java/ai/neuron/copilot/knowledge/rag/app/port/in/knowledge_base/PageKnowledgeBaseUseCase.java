package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query.PageKnowledgeBaseQuery;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;

public interface PageKnowledgeBaseUseCase {

    PageResult<KnowledgeBase> execute(PageKnowledgeBaseQuery query);

}
