package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter.SysTenantDifyRegisterConverter;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.SysTenantDifyRegisterPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.SysTenantDifyRegisterPORepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.SysTenantDifyRegisterRepository;
import ai.neuron.copilot.knowledge.rag.domain.sys.model.SysTenantDifyRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SysTenantDifyRegisterRepositoryImpl implements SysTenantDifyRegisterRepository {

    private final SysTenantDifyRegisterPORepository sysTenantDifyRegisterPORepository;

    @Override
    public Optional<SysTenantDifyRegister> fetchByTenantId(TenantId tenantId) {
        return sysTenantDifyRegisterPORepository.lambdaQuery()
                .eq(SysTenantDifyRegisterPO::getTenantId, tenantId.value())
                .oneOpt()
                .map(SysTenantDifyRegisterConverter::toDomain);
    }

}
