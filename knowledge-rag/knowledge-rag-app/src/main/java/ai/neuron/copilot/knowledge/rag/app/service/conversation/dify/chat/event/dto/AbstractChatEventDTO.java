package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "event",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(ChatEventWorkflowStartedDTO.class),
        @JsonSubTypes.Type(ChatEventWorkflowFinishedDTO.class),
        @JsonSubTypes.Type(ChatEventPingDTO.class),
        @JsonSubTypes.Type(ChatEventNodeStartedDTO.class),
        @JsonSubTypes.Type(ChatEventNodeFinishedDTO.class),
        @JsonSubTypes.Type(ChatEventMessageDTO.class),
        @JsonSubTypes.Type(ChatEventMessageEndDTO.class),
        @JsonSubTypes.Type(ChatEventErrorDTO.class)
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class AbstractChatEventDTO {

    String event;

}