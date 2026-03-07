package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CUSTOM,
        property = "ability",
        visible = true
)
@JsonTypeIdResolver(ConversationPayloadTypeIdResolver.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class ConversationPayloadDTO {

    String ability;

}
