package ai.neuron.copilot.knowledge.rag.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "knowledge_base", uniqueConstraints = {
		@UniqueConstraint(name = "knowledge_base_id_UNIQUE", columnNames = "knowledge_base_id")
})
public class KnowledgeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "knowledge_base_id", nullable = false, length = 255)
	private String knowledgeBaseId;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "dify_dataset_id", nullable = false, length = 255)
	private String difyDatasetId;

	@Column(name = "dify_app_id", nullable = false, length = 255)
	private String difyAppId;

	@Column(name = "dify_api_key", nullable = false, length = 255)
	private String difyApiKey;

	// 注意：将 tenant_id 改为 tenant_code
	@Column(name = "tenant_code", nullable = false, length = 255)
	private String tenantCode;

	@Column(name = "deleted", nullable = false, columnDefinition = "tinyint default 0")
	private Boolean deleted = false;

	@Column(name = "created_by", nullable = false, updatable = false)
	private Long createdBy;

	@Column(name = "created_at", nullable = false, updatable = false)
	private java.time.LocalDateTime createdAt;

	@Column(name = "updated_by", nullable = false)
	private Long updatedBy;

	@Column(name = "updated_at", nullable = false)
	private java.time.LocalDateTime updatedAt;

	@Column(name = "version")
	private Integer version;
}
