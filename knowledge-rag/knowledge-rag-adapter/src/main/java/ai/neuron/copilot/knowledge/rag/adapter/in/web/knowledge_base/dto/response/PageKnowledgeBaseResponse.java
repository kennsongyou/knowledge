package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.shared.KnowledgeBaseDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PageKnowledgeBaseResponse(PageResult<KnowledgeBaseDTO> result) {
}
