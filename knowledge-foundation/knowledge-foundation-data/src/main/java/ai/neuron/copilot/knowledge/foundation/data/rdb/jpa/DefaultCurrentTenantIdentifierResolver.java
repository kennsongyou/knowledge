package ai.neuron.copilot.knowledge.foundation.data.rdb.jpa;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import java.util.Optional;


public class DefaultCurrentTenantIdentifierResolver
        implements CurrentTenantIdentifierResolver<Long> {

    private static final Long SYSTEM_TENANT_ID = 0L;

    @Override
    public Long resolveCurrentTenantIdentifier() {
        return Optional.ofNullable(ContextHolder.tenant()).map(TenantContext::id).orElse(SYSTEM_TENANT_ID);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
