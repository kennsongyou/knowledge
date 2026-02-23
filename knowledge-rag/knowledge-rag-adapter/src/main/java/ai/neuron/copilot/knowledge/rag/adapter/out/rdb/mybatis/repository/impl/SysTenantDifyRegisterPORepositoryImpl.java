package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.impl;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.SysTenantDifyRegisterMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.SysTenantDifyRegisterPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.SysTenantDifyRegisterPORepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base.BasePORepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class SysTenantDifyRegisterPORepositoryImpl
        extends BasePORepositoryImpl<SysTenantDifyRegisterMapper, SysTenantDifyRegisterPO>
        implements SysTenantDifyRegisterPORepository {
}
