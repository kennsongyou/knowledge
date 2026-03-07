package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class ChatRequest {

    String query;

    Map<String, Object> inputs;

    String responseMode = "streaming";

    String user;

    String conversationId;

    Boolean autoGenerateName = true;

    String workflowId;

    String traceId;

}
