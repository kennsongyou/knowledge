package ai.neuron.copilot.knowledge.rag.adapter.out.http.sse.dto;

import ai.neuron.copilot.knowledge.foundation.core.json.JsonCodec;
import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseClientContext;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
public class DifyChatSseClientContext extends SseClientContext {

    private final String serverId;

    public DifyChatSseClientContext(JsonCodec jsonCodec, String serverId) {
        super(jsonCodec);
        this.serverId = serverId;
    }

}
