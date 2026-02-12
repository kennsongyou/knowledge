package ai.neuron.copilot.knowledge.foundation.data.rdb;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SoftDelete
@MappedSuperclass
public abstract class AuditingPO extends CreatedAuditingPO {

	@LastModifiedBy
	@Column(name = "updated_by", nullable = false, columnDefinition = "BIGINT NOT NULL")
	Long updatedBy;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	Instant updatedAt;

}
