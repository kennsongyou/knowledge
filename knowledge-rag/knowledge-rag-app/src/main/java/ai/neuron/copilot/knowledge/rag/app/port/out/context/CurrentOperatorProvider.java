package ai.neuron.copilot.knowledge.rag.app.port.out.context;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.UserId;

public interface CurrentOperatorProvider {

    UserId userId();

    TenantId tenantId();

    String traceId();

    String requestId();

}
