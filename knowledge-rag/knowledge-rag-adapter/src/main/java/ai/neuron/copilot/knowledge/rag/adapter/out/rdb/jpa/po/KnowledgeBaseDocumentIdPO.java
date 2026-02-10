package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class KnowledgeBaseDocumentIdPO implements Serializable {

    @Column(name = "knowledge_base_id", nullable = false, updatable = false)
    private String knowledgeBaseId;

    @Column(name = "document_id", nullable = false, updatable = false)
    private String documentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeBaseDocumentIdPO that)) return false;
        return Objects.equals(knowledgeBaseId, that.knowledgeBaseId)
                && Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knowledgeBaseId, documentId);
    }

}

