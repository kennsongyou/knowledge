package ai.neuron.copilot.knowledge.rag.domain.document.model;

import ai.neuron.copilot.knowledge.common.util.IdUtils;

import java.util.Objects;

public record DocumentId(String value) {

    public DocumentId {
        Objects.requireNonNull(value, "Document cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Document cannot be empty");
        }
    }

    public static DocumentId reconstitute(String value) {
        return new DocumentId(value);
    }

    public static DocumentId create() {
        return new DocumentId(IdUtils.uuidV7Str());
    }

    @Override
    public String toString() {
        return value;
    }

}
