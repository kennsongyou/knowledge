package ai.neuron.copilot.knowledge.foundation.web.sse.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SseServerMessage {
    
    String type;

    String action;
    
    Object data;
    
    Long timestamp;

    public static SseServerMessage create(String type, String action) {
        return create(type, action, null);
    }
    
    public static SseServerMessage create(String type, String action, Object data) {
        return SseServerMessage.builder()
            .type(type)
            .action(action)
            .data(data)
            .timestamp(System.currentTimeMillis())
            .build();
    }

    public static SseServerMessage heartbeat() {
        return SseServerMessage.builder()
            .type("system")
            .action("heartbeat")
            .timestamp(System.currentTimeMillis())
            .build();
    }
}