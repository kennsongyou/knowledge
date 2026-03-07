package ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.shared;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DocumentDTO(String id, String displayName, String extension) {
}
