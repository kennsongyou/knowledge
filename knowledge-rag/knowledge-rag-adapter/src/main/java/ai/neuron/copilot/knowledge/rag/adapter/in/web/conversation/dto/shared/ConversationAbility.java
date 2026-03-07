package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared;

import ai.neuron.copilot.knowledge.foundation.core.exception.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ConversationAbility {

    KBQA("kbqa", ConversationKBQAPayload.class),

    BI("bi", ConversationBIPayload.class);

    private final String ability;

    private final Class<? extends ConversationPayload> clazz;

    private static final Map<String, ConversationAbility> ABILITY_MAP =
        Arrays.stream(values()).collect(Collectors.toMap(ConversationAbility::getAbility, Function.identity()));

    private static final Map<Class<?>, ConversationAbility> CLASS_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(ConversationAbility::getClazz, Function.identity()));


    public static ConversationAbility fromType(String ability) {
        ConversationAbility result = ABILITY_MAP.get(ability);
        if (result == null) {
            throw new InvalidArgumentException();
        }
        return result;
    }

    public static ConversationAbility fromClass(Class<?> clazz) {
        ConversationAbility result = CLASS_MAP.get(clazz);
        if (result == null) {
            throw new InvalidArgumentException();
        }
        return result;
    }

}