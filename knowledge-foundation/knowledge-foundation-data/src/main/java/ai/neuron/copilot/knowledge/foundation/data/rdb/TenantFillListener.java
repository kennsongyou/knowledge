package ai.neuron.copilot.knowledge.foundation.data.rdb;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.util.Objects;

//@Component
//public class TenantFillListener {
//
//    @PrePersist
//    public void prePersist(Object entity) {
//        if (entity instanceof TenantAware tenantAware) {
//            tenantAware.setTenantId(Objects.requireNonNull(ContextHolder.tenant()).id());
//        }
//    }
//
//}
