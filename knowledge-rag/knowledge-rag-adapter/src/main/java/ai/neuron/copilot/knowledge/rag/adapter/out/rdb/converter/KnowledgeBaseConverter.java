package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;

public final class KnowledgeBaseConverter {

    public static KnowledgeBase toDomain(KnowledgeBasePO po) {
        return KnowledgeBase.reconstitute(
                KnowledgeBaseId.reconstitute(po.getKnowledgeBaseId()),
                KnowledgeBaseName.reconstitute(po.getName()),
                KnowledgeBaseDescription.reconstitute(po.getDescription()),
                KnowledgeBaseImpl.fromValue(po.getImpl()));
    }

    public static KnowledgeBasePO toPO(KnowledgeBase knowledgeBase) {
        KnowledgeBasePO po = new KnowledgeBasePO();
        po.setKnowledgeBaseId(knowledgeBase.getId().value());
        po.setName(knowledgeBase.getName().value());
        po.setDescription(knowledgeBase.getDescription().value());
        po.setImpl(knowledgeBase.getImpl().getValue());
        return po;
    }

}