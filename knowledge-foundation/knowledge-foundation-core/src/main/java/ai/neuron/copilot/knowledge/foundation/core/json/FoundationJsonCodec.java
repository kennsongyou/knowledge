package ai.neuron.copilot.knowledge.foundation.core.json;

import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoundationJsonCodec {

    private final ObjectMapper objectMapper;

    private final ObjectMapper snakeObjectMapper;

    public String encode(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    public String encodeSnake(Object obj) {
        try {
            return snakeObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    public <T> T decode(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    public <T> T decodeSnake(String json, Class<T> clazz) {
        try {
            return snakeObjectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    public <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

    public <T> T decodeSnake(String json, TypeReference<T> typeReference) {
        try {
            return snakeObjectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new SystemException(e);
        }
    }

}
