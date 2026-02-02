package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.shared.KnowledgeBaseDTO;

public record PageKnowledgeBaseResponse(PageResult<KnowledgeBaseDTO> result) {
}
