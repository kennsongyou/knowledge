package ai.neuron.copilot.knowledge.foundation.data.rdb;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingPO extends BasePO {

	@Column(name = "deleted_at")
	LocalDateTime deletedAt;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	Long createdBy;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	LocalDateTime createdAt;

	@LastModifiedBy
	@Column(name = "updated_by")
	Long updatedBy;

	@LastModifiedDate
	@Column(name = "updated_at")
	LocalDateTime updatedAt;

}
