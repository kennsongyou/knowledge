package ai.neuron.copilot.knowledge.foundation.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SnakeCaseJsonCodecImpl extends JsonCodecAdapter implements SnakeCaseJsonCodec {

    public SnakeCaseJsonCodecImpl(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}
