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

    final TenantId tenantId;

    private KnowledgeBase(KnowledgeBaseId id, KnowledgeBaseName name, KnowledgeBaseDescription description,
                          DifyDatasetId difyDatasetId, TenantId tenantId) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.description = description;
        this.difyDatasetId = Objects.requireNonNull(difyDatasetId);
        this.tenantId = Objects.requireNonNull(tenantId);
    }

    public void rename(KnowledgeBaseName knowledgeBaseName) {
        this.name = knowledgeBaseName;
    }

    public void changeDescription(KnowledgeBaseDescription description) {
        this.description = description;
    }

    public static KnowledgeBase reconstitute(KnowledgeBaseId knowledgeBaseId, KnowledgeBaseName knowledgeBaseName,
                                             KnowledgeBaseDescription description, DifyDatasetId difyDatasetId,
                                             TenantId tenantId) {
        return new KnowledgeBase(knowledgeBaseId, knowledgeBaseName, description, difyDatasetId, tenantId);
    }

    public static KnowledgeBase create(KnowledgeBaseName knowledgeBaseName, KnowledgeBaseDescription description,
                                       DifyDatasetId difyDatasetId, TenantId tenantId) {
        return new KnowledgeBase(KnowledgeBaseId.create(), knowledgeBaseName, description, difyDatasetId, tenantId);
    }

}
