package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CreateKnowledgeBaseResponse(String knowledgeBaseId) {
}
