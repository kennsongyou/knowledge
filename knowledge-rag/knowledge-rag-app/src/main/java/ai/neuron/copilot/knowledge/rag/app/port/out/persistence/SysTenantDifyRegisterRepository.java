package ai.neuron.copilot.knowledge.rag.app.port.out.persistence;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.domain.sys.model.SysTenantDifyRegister;

import java.util.Optional;

public interface SysTenantDifyRegisterRepository {

    Optional<SysTenantDifyRegister> fetchByTenantId(TenantId tenantId);

}
