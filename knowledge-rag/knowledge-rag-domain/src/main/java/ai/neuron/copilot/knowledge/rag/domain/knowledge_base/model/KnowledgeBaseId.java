package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import java.util.Objects;

public record KnowledgeBaseId(String value) {

    public KnowledgeBaseId {
        Objects.requireNonNull(value, "KnowledgeBaseId cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("KnowledgeBaseId cannot be empty");
        }
    }

    public static KnowledgeBaseId reconstitute(String value) {
        return new KnowledgeBaseId(value);
    }

    public static KnowledgeBaseId create() {
        return new KnowledgeBaseId(IdUtils.uuidStr());
    }

    @Override
    public String toString() {
        return value;
    }

}
