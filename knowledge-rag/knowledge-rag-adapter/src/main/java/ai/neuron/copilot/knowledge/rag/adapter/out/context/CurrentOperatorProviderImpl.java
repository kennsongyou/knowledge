package ai.neuron.copilot.knowledge.rag.adapter.out.context;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.RequestContext;
import ai.neuron.copilot.knowledge.foundation.core.context.TenantContext;
import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.UserId;
import ai.neuron.copilot.knowledge.rag.app.port.out.context.CurrentOperatorProvider;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrentOperatorProviderImpl implements CurrentOperatorProvider {

    @Override
    public UserId userId() {
        return Optional.ofNullable(ContextHolder.user()).map(UserContext::id).orElse(null);
    }

    @Override
    public TenantId tenantId() {
        return Optional.ofNullable(ContextHolder.tenant()).map(TenantContext::id).map(TenantId::reconstitute)
                .orElse(null);
    }

    @Override
    public String traceId() {
        return Optional.ofNullable(ContextHolder.request()).map(RequestContext::traceId).orElse(null);
    }

    @Override
    public String requestId() {
        return Optional.ofNullable(ContextHolder.request()).map(RequestContext::requestId).orElse(null);
    }

}
