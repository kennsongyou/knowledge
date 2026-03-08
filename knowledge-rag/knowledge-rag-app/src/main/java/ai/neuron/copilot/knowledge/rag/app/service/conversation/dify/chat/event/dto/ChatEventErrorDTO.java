package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonTypeName("error")
public class ChatEventErrorDTO extends AbstractChatEventDTO {

    String taskId;

    String messageId;

    int status;

    String code;

    String message;

}
