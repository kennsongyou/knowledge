package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class KnowledgeBase {

    final KnowledgeBaseId id;

    String name;

    String description;

    final DifyDatasetId difyDatasetId;

    private KnowledgeBase(KnowledgeBaseId id, String name, String description, DifyDatasetId difyDatasetId) {
        this.id = Objects.requireNonNull(id);
        this.name = requireValidName(name);
        this.description = requireValidDescription(description);
        this.difyDatasetId = Objects.requireNonNull(difyDatasetId);
    }

    public void rename(String name) {
        this.name = requireValidName(name);
    }

    public void changeDescription(String description) {
        this.description = requireValidDescription(description);
    }

    public static KnowledgeBase reconstitute(KnowledgeBaseId knowledgeBaseId, String name, String description, DifyDatasetId difyDatasetId) {
        return new KnowledgeBase(knowledgeBaseId, name, description, difyDatasetId);
    }

    public static KnowledgeBase create(String name, String description, DifyDatasetId difyDatasetId) {
        return new KnowledgeBase(KnowledgeBaseId.create(), name, description, difyDatasetId);
    }

    private static String requireValidName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Knowledge base name cannot be empty");
        }
        if (StringUtils.length(name) > 32) {
            throw new IllegalArgumentException("Knowledge base name is too long (max 32 characters)");
        }
        return name;
    }

    private static String requireValidDescription(String description) {
        if (StringUtils.length(description) > 255) {
            throw new IllegalArgumentException("Knowledge base description is too long (max 255 characters)");
        }
        return description;
    }

}
