package ai.neuron.copilot.knowledge.rag.domain.conversation.model;

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

    KBQA("kbqa"),

    BI("bi");

    private final String ability;

    private static final Map<String, ConversationAbility> ABILITY_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(ConversationAbility::getAbility, Function.identity()));

    public static ConversationAbility fromType(String ability) {
        ConversationAbility result = ABILITY_MAP.get(ability);
        if (result == null) {
            throw new InvalidArgumentException();
        }
        return result;
    }

}