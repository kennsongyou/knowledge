package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.impl;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.KnowledgeBaseMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.KnowledgeBasePORepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base.BasePORepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class KnowledgeBasePORepositoryImpl extends BasePORepositoryImpl<KnowledgeBaseMapper, KnowledgeBasePO>
        implements KnowledgeBasePORepository {
}
