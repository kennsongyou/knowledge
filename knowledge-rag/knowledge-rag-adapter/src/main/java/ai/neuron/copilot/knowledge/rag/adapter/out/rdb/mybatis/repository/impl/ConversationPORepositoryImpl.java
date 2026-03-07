package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.impl;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.ConversationMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.ConversationPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.ConversationPORepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base.BasePORepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ConversationPORepositoryImpl extends BasePORepositoryImpl<ConversationMapper, ConversationPO>
        implements ConversationPORepository {
}
