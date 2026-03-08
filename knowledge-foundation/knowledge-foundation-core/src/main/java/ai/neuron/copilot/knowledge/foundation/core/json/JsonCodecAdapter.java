package ai.neuron.copilot.knowledge.foundation.core.json;

import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonCodecAdapter implements JsonCodec {

    private final ObjectMapper objectMapper;

    @Override
    public String encode(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public <T> T decode(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    public <T> T decode(String json, JavaType javaType) {
        try {
            return objectMapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public <T> T convert(Object value, TypeReference<T> typeReference) {
        return objectMapper.convertValue(value, typeReference);
    }

}
