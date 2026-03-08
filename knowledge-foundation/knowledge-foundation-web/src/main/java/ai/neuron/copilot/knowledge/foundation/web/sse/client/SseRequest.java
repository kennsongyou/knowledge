package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class SseRequest {

    private String url;

    @Builder.Default
    private String method = "GET";

    private Map<String, Object> headers;

    private String jsonBody;

}