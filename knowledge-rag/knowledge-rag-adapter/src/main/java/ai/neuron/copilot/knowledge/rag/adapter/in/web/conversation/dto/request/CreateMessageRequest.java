package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.request;

import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared.ConversationPayload;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateMessageRequest {

    String conversationId;

    String connectionId;

    @NotBlank
    String query;

    ConversationPayload payload;

}
