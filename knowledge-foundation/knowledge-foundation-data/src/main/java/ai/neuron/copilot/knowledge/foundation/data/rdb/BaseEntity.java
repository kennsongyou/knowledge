package ai.neuron.copilot.knowledge.foundation.data.rdb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, CustomAwareAuditingEntityListener.class})
public abstract class BaseEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tenant_code", nullable = false)
	private String tenantCode;

	@Column(name = "deleted", nullable = false, columnDefinition = "tinyint default 0")
	private Boolean deleted;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedBy
	@Column(name = "updated_by")
	private Long updatedBy;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
