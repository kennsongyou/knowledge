package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po;

import ai.neuron.copilot.knowledge.foundation.data.rdb.AuditingEntity;
import ai.neuron.copilot.knowledge.foundation.data.rdb.TenantAware;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SQLRestriction("deleted_at IS NULL")
@Table(name = "knowledge_base", uniqueConstraints = {
		@UniqueConstraint(name = "knowledge_base_id_UNIQUE", columnNames = "knowledge_base_id")
})
public final class KnowledgeBasePO extends AuditingEntity implements TenantAware {

	@Column(name = "knowledge_base_id", nullable = false)
	String knowledgeBaseId;

	@Column(name = "name", nullable = false)
	String name;

	@Column(name = "description", columnDefinition = "text")
	String description;

	@Column(name = "dify_dataset_id", nullable = false)
	String difyDatasetId;

	@Column(name = "tenant_id", nullable = false)
	Long tenantId;

}
