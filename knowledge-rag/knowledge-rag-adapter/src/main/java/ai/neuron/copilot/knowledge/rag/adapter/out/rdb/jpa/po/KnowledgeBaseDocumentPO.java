package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po;

import ai.neuron.copilot.knowledge.foundation.data.rdb.CreatedAuditingPO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "knowledge_base_document", uniqueConstraints = {
		@UniqueConstraint(name = "knowledge_base_id_document_id_UNIQUE", columnNames = {"knowledge_base_id", "document_id"})
})
public final class KnowledgeBaseDocumentPO extends CreatedAuditingPO {

	@EmbeddedId
    KnowledgeBaseDocumentIdPO knowledgeBaseDocumentIdPO;

}
