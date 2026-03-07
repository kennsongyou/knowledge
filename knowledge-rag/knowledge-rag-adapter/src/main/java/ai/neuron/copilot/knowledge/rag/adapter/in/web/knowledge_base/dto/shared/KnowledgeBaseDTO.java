package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.shared;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KnowledgeBaseDTO(String id, String name, String description, String impl) {
}
