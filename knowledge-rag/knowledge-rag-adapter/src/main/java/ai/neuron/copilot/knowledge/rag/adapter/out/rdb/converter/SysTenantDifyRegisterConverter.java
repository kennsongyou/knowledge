package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.SysTenantDifyRegisterPO;
import ai.neuron.copilot.knowledge.rag.domain.sys.model.SysTenantDifyRegister;

public final class SysTenantDifyRegisterConverter {

    public static SysTenantDifyRegister toDomain(SysTenantDifyRegisterPO po) {
        return new SysTenantDifyRegister(po.getTenantId(), po.getDatasetId(), po.getDatasetMetadata(), po.getAppId(),
                po.getAppApiKey());
    }

    public static SysTenantDifyRegisterPO toPO(SysTenantDifyRegister model) {
        SysTenantDifyRegisterPO po = new SysTenantDifyRegisterPO();
        po.setTenantId(model.tenantId());
        po.setDatasetId(model.datasetId());
        po.setDatasetMetadata(model.datasetMetadata());
        po.setAppId(model.appId());
        po.setAppApiKey(model.appApiKey());
        return po;
    }

}