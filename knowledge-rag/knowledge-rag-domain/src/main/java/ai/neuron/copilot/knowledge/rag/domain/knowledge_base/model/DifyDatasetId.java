package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import java.util.Objects;

public record DifyDatasetId(String value) {

    public DifyDatasetId {
        Objects.requireNonNull(value, "DifyDatasetId cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("DifyDatasetId cannot be empty");
        }
    }

    public static DifyDatasetId reconstitute(String value) {
        return new DifyDatasetId(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
