package ai.neuron.copilot.knowledge.foundation.core.json;

import com.fasterxml.jackson.core.type.TypeReference;

public interface JsonCodec {

    String encode(Object obj);

    <T> T decode(String json, Class<T> clazz);

    <T> T decode(String json, TypeReference<T> typeReference);

    <T> T convert(Object obj, TypeReference<T> typeReference);

}
