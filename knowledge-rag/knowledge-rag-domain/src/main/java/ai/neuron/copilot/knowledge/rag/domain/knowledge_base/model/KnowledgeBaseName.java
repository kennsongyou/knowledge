package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public record KnowledgeBaseName(String value) {

    public KnowledgeBaseName {
        Objects.requireNonNull(value, "Knowledge base name cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Knowledge base name cannot be empty");
        }
        if (StringUtils.length(value) > 32) {
            throw new IllegalArgumentException("Knowledge base name is too long (max 32 characters)");
        }
    }

    public static KnowledgeBaseName reconstitute(String value) {
        return new KnowledgeBaseName(value);
    }

    public static KnowledgeBaseName create(String value) {
        return new KnowledgeBaseName(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
