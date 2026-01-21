package ai.neuron.copilot.knowledge.foundation.data.rdb;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Optional;

public class CustomAwareAuditingEntityListener {

    @PrePersist
    @PreUpdate
    public void setTenantCode(Object entity) {
        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setTenantCode(Optional.ofNullable(ContextHolder.user()).map(UserContext::tenantCode).orElse(null));
        }
    }
}
