package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class KnowledgeBase {

    final KnowledgeBaseId id;

    KnowledgeBaseName name;

    KnowledgeBaseDescription description;

    final DifyDatasetId difyDatasetId;

    private KnowledgeBase(KnowledgeBaseId id, KnowledgeBaseName name, KnowledgeBaseDescription description,
                          DifyDatasetId difyDatasetId) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.description = description;
        this.difyDatasetId = Objects.requireNonNull(difyDatasetId);
    }

    public void rename(KnowledgeBaseName knowledgeBaseName) {
        this.name = knowledgeBaseName;
    }

    public void changeDescription(KnowledgeBaseDescription description) {
        this.description = description;
    }

    public static KnowledgeBase reconstitute(KnowledgeBaseId knowledgeBaseId, KnowledgeBaseName knowledgeBaseName,
                                             KnowledgeBaseDescription description, DifyDatasetId difyDatasetId) {
        return new KnowledgeBase(knowledgeBaseId, knowledgeBaseName, description, difyDatasetId);
    }

    public static KnowledgeBase create(KnowledgeBaseName knowledgeBaseName, KnowledgeBaseDescription description,
                                       DifyDatasetId difyDatasetId) {
        return new KnowledgeBase(KnowledgeBaseId.create(), knowledgeBaseName, description, difyDatasetId);
    }

}
