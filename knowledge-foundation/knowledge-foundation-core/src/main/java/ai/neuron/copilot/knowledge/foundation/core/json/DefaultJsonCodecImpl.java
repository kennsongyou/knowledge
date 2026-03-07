package ai.neuron.copilot.knowledge.foundation.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultJsonCodecImpl extends JsonCodecAdapter implements DefaultJsonCodec {

    public DefaultJsonCodecImpl(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}
