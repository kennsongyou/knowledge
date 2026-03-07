package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class ConversationPayloadTypeIdResolver extends TypeIdResolverBase {

    @Override
    public JavaType typeFromId(DatabindContext context, String ability) {
        Class<? extends ConversationPayload> clazz = ConversationAbility.fromType(ability).getClazz();
        return context.constructType(clazz);
    }

    @Override
    public String idFromValue(Object value) {
        return ((ConversationPayload) value).getAbility();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}