package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum KnowledgeBaseImpl {

    DIFY("dify");

    private final String value;

    private static final Map<String, KnowledgeBaseImpl> CACHE =
            Arrays.stream(values())
                    .collect(Collectors.toUnmodifiableMap(
                            KnowledgeBaseImpl::getValue,
                            Function.identity()
                    ));

    public static KnowledgeBaseImpl fromValue(String value) {
        KnowledgeBaseImpl result = CACHE.get(value);
        if (result == null) {
            throw new IllegalArgumentException("Unknown knowledge base impl: " + value);
        }
        return result;
    }

}
