package ai.neuron.copilot.knowledge.foundation.data.rdb;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

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
