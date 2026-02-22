package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

public record DifyKnowledgeBase(KnowledgeBaseId KnowledgeBaseId, DifyDatasetId difyDatasetId) {

    public static DifyKnowledgeBase create(KnowledgeBaseId KnowledgeBaseId, DifyDatasetId difyDatasetId) {
        return new DifyKnowledgeBase(KnowledgeBaseId, difyDatasetId);
    }

}
