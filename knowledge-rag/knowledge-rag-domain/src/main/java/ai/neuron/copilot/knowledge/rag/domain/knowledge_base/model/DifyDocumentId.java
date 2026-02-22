package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import java.util.Objects;

public record DifyDocumentId(String value) {

    public DifyDocumentId {
        Objects.requireNonNull(value, "DifyDocumentId cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("DifyDocumentId cannot be empty");
        }
    }

    public static DifyDocumentId reconstitute(String value) {
        return new DifyDocumentId(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
