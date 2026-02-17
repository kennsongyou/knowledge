package ai.neuron.copilot.knowledge.foundation.data.rdb.jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.TenantId;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class BusinessPO {

    @TenantId
    @Column(name = "tenant_id", nullable = false)
    Long tenantId;

}
