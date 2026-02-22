package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.impl;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.DifyKnowledgeBaseMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DifyKnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.DifyKnowledgeBasePORepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base.BasePORepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class DifyKnowledgeBasePORepositoryImpl extends BasePORepositoryImpl<DifyKnowledgeBaseMapper, DifyKnowledgeBasePO>
        implements DifyKnowledgeBasePORepository {
}
