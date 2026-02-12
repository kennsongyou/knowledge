package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po;

import ai.neuron.copilot.knowledge.foundation.data.rdb.AuditingPO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "knowledge_base", uniqueConstraints = {
		@UniqueConstraint(name = "knowledge_base_id_UNIQUE", columnNames = "knowledge_base_id"),
		@UniqueConstraint(name = "tenant_id_name_UNIQUE", columnNames = {"tenant_id", "name"})
})
public final class KnowledgeBasePO extends AuditingPO {

	@Id
	@Column(name = "knowledge_base_id", nullable = false, unique = true, updatable = false)
	String knowledgeBaseId;

	@Column(name = "name", nullable = false)
	String name;

	@Column(name = "description", columnDefinition = "text")
	String description;

	@Column(name = "dify_dataset_id", nullable = false)
	String difyDatasetId;

}
