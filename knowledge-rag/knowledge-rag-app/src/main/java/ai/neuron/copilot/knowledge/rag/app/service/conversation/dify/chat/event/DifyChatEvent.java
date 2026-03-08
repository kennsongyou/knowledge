package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event;

import ai.neuron.copilot.knowledge.foundation.core.exception.InvalidArgumentException;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.chat.event.dto.*;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum DifyChatEvent {

    PING("ping"),

    WORKFLOW_STARTED("workflow_started"),

    WORKFLOW_FINISHED("workflow_finished"),

    NODE_STARTED("node_started"),

    NODE_FINISHED("node_finished"),

    MESSAGE("message"),

    MESSAGE_END("message_end"),

    ERROR("error");

    String event;

    private static final Map<String, DifyChatEvent> EVENT_MAP =
            Arrays.stream(values())
                    .collect(Collectors.toMap(
                            DifyChatEvent::getEvent,
                            Function.identity()
                    ));

    public static DifyChatEvent fromEvent(String event) {
        return EVENT_MAP.get(event);
    }

}
