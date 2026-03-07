package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.impl;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.ConversationMessageMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.ConversationMessagePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.ConversationMessagePORepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base.BasePORepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ConversationMessagePORepositoryImpl
        extends BasePORepositoryImpl<ConversationMessageMapper, ConversationMessagePO>
        implements ConversationMessagePORepository {
}
