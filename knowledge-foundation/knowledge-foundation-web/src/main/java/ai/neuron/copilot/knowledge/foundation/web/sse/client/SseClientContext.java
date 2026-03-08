package ai.neuron.copilot.knowledge.foundation.web.sse.client;

import ai.neuron.copilot.knowledge.foundation.core.json.JsonCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public class SseClientContext {

    JsonCodec jsonCodec;

    Map<String, Object> attributes = new ConcurrentHashMap<>();

    public SseClientContext(JsonCodec jsonCodec) {
        this.jsonCodec = jsonCodec;
    }

    public void put(String key, Object value) {
        attributes.put(key, value);
    }

    public Object get(String key) {
        return attributes.get(key);
    }

    public <T> T get(String key, Class<T> type) {
        Object value = attributes.get(key);
        return value == null ? null : type.cast(value);
    }

    public <T> T get(String key, TypeReference<T> typeReference) {
        Object value = attributes.get(key);
        if (value == null) {
            return null;
        }
        return jsonCodec.convert(value, typeReference);
    }

    public void remove(String key) {
        attributes.remove(key);
    }
}
