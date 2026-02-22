package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;


public interface KnowledgeBaseImplementer {

    default void create(KnowledgeBase knowledgeBase) {}

    default void delete(KnowledgeBase knowledgeBase) {}

    void createDocument(KnowledgeBase knowledgeBase, Document document);

    void deleteDocument(KnowledgeBase knowledgeBase, Document document);

    KnowledgeBaseImpl impl();
}
