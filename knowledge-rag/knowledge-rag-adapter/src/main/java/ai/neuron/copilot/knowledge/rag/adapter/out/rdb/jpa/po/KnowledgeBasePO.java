package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po;

import ai.neuron.copilot.knowledge.foundation.data.rdb.AuditingEntity;
import ai.neuron.copilot.knowledge.foundation.data.rdb.TenantAware;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "knowledge_base", uniqueConstraints = {
		@UniqueConstraint(name = "knowledge_base_id_UNIQUE", columnNames = "knowledge_base_id")
})
public class KnowledgeBasePO extends AuditingEntity implements TenantAware {

	@Column(name = "knowledge_base_id", nullable = false)
	private String knowledgeBaseId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "dify_dataset_id", nullable = false)
	private String difyDatasetId;

	@Column(name = "tenant_id", nullable = false)
	private Long tenantId;

}
