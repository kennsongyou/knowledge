package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DifyKnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.DifyKnowledgeBasePORepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DifyKnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyKnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DifyKnowledgeBaseRepositoryImpl implements DifyKnowledgeBaseRepository {

    private final DifyKnowledgeBasePORepository difyKnowledgeBasePORepository;

    @Override
    public boolean save(DifyKnowledgeBase difyKnowledgeBase) {
        DifyKnowledgeBasePO po = new DifyKnowledgeBasePO();
        po.setDifyKnowledgeBaseId(IdUtils.uuidV7Str());
        po.setKnowledgeBaseId(difyKnowledgeBase.KnowledgeBaseId().value());
        po.setExternalDatasetId(difyKnowledgeBase.difyDatasetId().value());
        return difyKnowledgeBasePORepository.save(po);
    }

    public boolean delete(KnowledgeBaseId knowledgeBaseId) {
        return difyKnowledgeBasePORepository.lambdaUpdate()
                .eq(DifyKnowledgeBasePO::getKnowledgeBaseId, knowledgeBaseId.value())
                .remove();
    }

}
