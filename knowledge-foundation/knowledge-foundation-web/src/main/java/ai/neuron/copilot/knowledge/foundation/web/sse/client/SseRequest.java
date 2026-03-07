package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SseRequest {

    private String url;

    private String method = "GET";

    private Map<String, Object> headers;

    private String jsonBody;

}