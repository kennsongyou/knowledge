package ai.neuron.copilot.knowledge.rag.app.port.out.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public enum DatasetMetadata {

    NEURON_KNOWLEDGE_BASE_ID("neuron_knowledge_base_id");

    private final String name;

    private static final Map<String, DatasetMetadata> LOOKUP =
            Arrays.stream(values())
                    .collect(Collectors.toUnmodifiableMap(
                            DatasetMetadata::getName,
                            Function.identity()
                    ));

    public static DatasetMetadata fromName(String key) {
        DatasetMetadata metadata = LOOKUP.get(key);
        if (metadata == null) {
            throw new IllegalArgumentException("Unknown dataset metadata key: " + key);
        }
        return metadata;
    }
}
