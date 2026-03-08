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
@JsonTypeName("workflow_finished")
public class ChatEventWorkflowFinishedDTO extends AbstractChatEventDTO {

    String taskId;

    String workflowRunId;

    Data data;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Data {

        String id;

        String workflowId;

        String status;

        Object outputs;

        String error;

        Double elapsedTime;

        int totalTokens;

        int totalSteps;

        long createdAt;

        long finishedAt;

    }

}
