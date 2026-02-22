package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

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

    final KnowledgeBaseImpl impl;

    private KnowledgeBase(KnowledgeBaseId id, KnowledgeBaseName name, KnowledgeBaseDescription description,
                          KnowledgeBaseImpl impl) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.description = description;
        this.impl = impl;
    }

    public void rename(KnowledgeBaseName knowledgeBaseName) {
        this.name = knowledgeBaseName;
    }

    public void changeDescription(KnowledgeBaseDescription description) {
        this.description = description;
    }

    public static KnowledgeBase reconstitute(KnowledgeBaseId knowledgeBaseId, KnowledgeBaseName knowledgeBaseName,
                                             KnowledgeBaseDescription description, KnowledgeBaseImpl impl) {
        return new KnowledgeBase(knowledgeBaseId, knowledgeBaseName, description, impl);
    }

    public static KnowledgeBase create(KnowledgeBaseName knowledgeBaseName, KnowledgeBaseDescription description,
                                       KnowledgeBaseImpl impl) {
        return new KnowledgeBase(KnowledgeBaseId.create(), knowledgeBaseName, description, impl);
    }

}
