package ai.neuron.copilot.knowledge.foundation.data.rdb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@SoftDelete(
		strategy = SoftDeleteType.ACTIVE,
		columnName = "deleted_at"
)
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingEntity extends BaseEntity {

	@Column(name = "deleted_at")
	private LocalDateTime deleted_at;

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
