package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import org.apache.commons.lang3.StringUtils;

public record KnowledgeBaseDescription(String value) {

    public KnowledgeBaseDescription {
        if (StringUtils.length(value) > 255) {
            throw new IllegalArgumentException("Knowledge base description is too long (max 255 characters)");
        }
    }

    public static KnowledgeBaseDescription reconstitute(String value) {
        return new KnowledgeBaseDescription(value);
    }

    public static KnowledgeBaseDescription create(String value) {
        return new KnowledgeBaseDescription(value);
    }

    @Override
    public String toString() {
        return value;
    }

}
