package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po;

import ai.neuron.copilot.knowledge.foundation.data.rdb.AuditingPO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.TenantId;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SQLRestriction("deleted = 0")
@Table(name = "document", uniqueConstraints = {
		@UniqueConstraint(name = "document_id_UNIQUE", columnNames = "document_id")
})
public final class DocumentPO extends AuditingPO {

	@Id
	@Column(name = "document_id", nullable = false, unique = true, updatable = false)
	String documentId;

	@Column(name = "original_file_name", nullable = false)
	String originalFileName;

	@Column(name = "display_name", nullable = false)
	String displayName;

	@Column(name = "extension")
	String extension;

	@Column(name = "object_key", nullable = false)
	String objectKey;

	@Column(name = "blob_provider", nullable = false)
	String blobProvider;

	@TenantId
	@Column(name = "tenant_id", nullable = false)
	Long tenantId;

}
