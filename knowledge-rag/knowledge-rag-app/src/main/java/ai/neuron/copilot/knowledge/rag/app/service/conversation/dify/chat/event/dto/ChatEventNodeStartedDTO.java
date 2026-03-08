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
@JsonTypeName("node_started")
public class ChatEventNodeStartedDTO extends AbstractChatEventDTO {

    String taskId;

    String workflowRunId;

    Data data;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Data {

        String id;

        String nodeId;

        String nodeType;

        String title;

        int index;

        String predecessorNodeId;

        Object inputs;

        long createdAt;

    }

}
