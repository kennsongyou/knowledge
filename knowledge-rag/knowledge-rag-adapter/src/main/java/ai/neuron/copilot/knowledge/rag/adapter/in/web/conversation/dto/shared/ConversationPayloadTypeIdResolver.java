package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared;

import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationAbility;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class ConversationPayloadTypeIdResolver extends TypeIdResolverBase {

    @Override
    public JavaType typeFromId(DatabindContext context, String ability) {

        ConversationAbility conversationAbility = ConversationAbility.fromType(ability);
        Class<? extends ConversationPayloadDTO> clazz = switch (conversationAbility) {
            case KBQA -> ConversationKbqaPayloadDTO.class;
            case BI -> throw new UnsupportedOperationException("Unsupported conversation ability: " + ability);
        };
        return context.constructType(clazz);
    }

    @Override
    public String idFromValue(Object value) {
        return ((ConversationPayloadDTO) value).getAbility();
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