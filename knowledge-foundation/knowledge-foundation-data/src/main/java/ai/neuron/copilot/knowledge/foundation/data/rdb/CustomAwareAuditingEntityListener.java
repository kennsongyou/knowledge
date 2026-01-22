package ai.neuron.copilot.knowledge.foundation.data.rdb;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


public class CustomAwareAuditingEntityListener {

    @PrePersist
    @PreUpdate
    public void handle(Object entity) {
    }

}
