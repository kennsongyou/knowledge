package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DifyKnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.DifyKnowledgeBasePORepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DifyKnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DifyKnowledgeBaseRepositoryImpl implements DifyKnowledgeBaseRepository {

    private final DifyKnowledgeBasePORepository difyKnowledgeBasePORepository;

    public boolean delete(KnowledgeBaseId knowledgeBaseId) {
        return difyKnowledgeBasePORepository.lambdaUpdate()
                .eq(DifyKnowledgeBasePO::getKnowledgeBaseId, knowledgeBaseId.value())
                .remove();
    }

}
