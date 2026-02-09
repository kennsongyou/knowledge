package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mapper;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;

public final class KnowledgeBaseMapper {

    public static KnowledgeBase toDomain(KnowledgeBasePO po) {
        return KnowledgeBase.reconstitute(
                KnowledgeBaseId.reconstitute(po.getKnowledgeBaseId()),
                KnowledgeBaseName.reconstitute(po.getName()),
                KnowledgeBaseDescription.reconstitute(po.getDescription()),
                DifyDatasetId.reconstitute(po.getDifyDatasetId()),
                TenantId.reconstitute(po.getTenantId()));
    }

    public static KnowledgeBasePO toPO(KnowledgeBase knowledgeBase) {
        KnowledgeBasePO po = new KnowledgeBasePO();
        po.setKnowledgeBaseId(knowledgeBase.getId().value());
        po.setName(knowledgeBase.getName().value());
        po.setDescription(knowledgeBase.getDescription().value());
        po.setDifyDatasetId(knowledgeBase.getDifyDatasetId().value());
        po.setTenantId(knowledgeBase.getTenantId().value());
        return po;
    }

}