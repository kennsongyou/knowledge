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

import java.time.Instant;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class AuditingPO extends CreatedAuditingPO {

	@Column(name = "deleted", nullable = false, columnDefinition = "TINYINT NOT NULL DEFAULT 0")
	boolean deleted;

	@LastModifiedBy
	@Column(name = "updated_by", nullable = false, columnDefinition = "BIGINT NOT NULL")
	long updatedBy;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	Instant updatedAt;

}
