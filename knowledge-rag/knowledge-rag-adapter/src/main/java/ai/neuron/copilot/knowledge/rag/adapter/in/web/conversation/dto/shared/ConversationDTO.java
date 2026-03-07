package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ConversationDTO(String id, String name) {
}
