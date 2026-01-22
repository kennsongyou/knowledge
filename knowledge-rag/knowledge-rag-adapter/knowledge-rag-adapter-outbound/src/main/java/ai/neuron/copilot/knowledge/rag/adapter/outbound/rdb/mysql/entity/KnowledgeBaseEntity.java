package ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.mysql.entity;

import ai.neuron.copilot.knowledge.foundation.data.rdb.AuditingEntity;
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
public class KnowledgeBaseEntity extends AuditingEntity {

	@Column(name = "knowledge_base_id", nullable = false)
	private String knowledgeBaseId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "dify_dataset_id", nullable = false)
	private String difyDatasetId;

	@Column(name = "dify_app_id", nullable = false)
	private String difyAppId;

	@Column(name = "dify_api_key", nullable = false)
	private String difyApiKey;

	@Column(name = "tenant_id", nullable = false)
	private Long tenantId;

}
