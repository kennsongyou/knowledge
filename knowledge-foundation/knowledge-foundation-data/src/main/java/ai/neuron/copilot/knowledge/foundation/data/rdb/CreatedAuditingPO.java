package ai.neuron.copilot.knowledge.foundation.data.rdb;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreatedAuditingPO extends BasePO {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected Long createdBy;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    protected Instant createdAt;

}