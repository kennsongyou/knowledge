package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl
        implements CurrentTenantIdentifierResolver<Long> {

    @Override
    public Long resolveCurrentTenantIdentifier() {
        return ContextHolder.tenant().id();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
