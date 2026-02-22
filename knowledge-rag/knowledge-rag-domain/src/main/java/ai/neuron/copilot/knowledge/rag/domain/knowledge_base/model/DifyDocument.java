package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

public record DifyDocument(DifyDatasetId difyDatasetId, DifyDocumentId difyDocumentId) {

    public static DifyDocument reconstitute(DifyDatasetId difyDatasetId, DifyDocumentId difyDocumentId) {
        return new DifyDocument(difyDatasetId, difyDocumentId);
    }

}
