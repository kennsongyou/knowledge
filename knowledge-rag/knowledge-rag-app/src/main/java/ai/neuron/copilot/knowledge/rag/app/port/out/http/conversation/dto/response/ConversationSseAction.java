package ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ConversationSseAction {

    TASK_STARTED("task_started"),

    TASK_FINISHED("task_finished"),

    NODE_STARTED("node_started"),

    NODE_FINISHED("node_finished"),

    DELTA("delta"),

    RESULT("result"),

    CITATIONS("citations");

    String name;

}
