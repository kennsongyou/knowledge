package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.data.page.CursorQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.CursorResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter.ConversationConverter;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.ConversationPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.ConversationPORepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.ConversationRepository;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ConversationRepositoryImpl implements ConversationRepository {

    private final ConversationPORepository conversationPORepository;

    @Override
    public CursorResult<Conversation> cursor(CursorQuery query) {
        int fetchSize = query.limit() + 1;
        List<ConversationPO> listed = conversationPORepository.lambdaQuery()
                .lt(StringUtils.isNotBlank(query.cursor()), ConversationPO::getConversationId, query.cursor())
                .orderByDesc(ConversationPO::getConversationId)
                .last("limit " + fetchSize).list();
        if (listed.size() <= query.limit()) {
            return new CursorResult<>(listed.stream().map(ConversationConverter::toDomain).toList(), null);
        }
        List<ConversationPO> records = listed.subList(0, query.limit());
        return new CursorResult<>(
                records.stream().map(ConversationConverter::toDomain).toList(),
                records.getLast().getConversationId()
        );
    }

    @Override
    public Optional<Conversation> fetch(ConversationId id) {
        return conversationPORepository.lambdaQuery().eq(ConversationPO::getConversationId, id).oneOpt()
                .map(ConversationConverter::toDomain);
    }

    @Override
    public boolean save(Conversation conversation) {
        return conversationPORepository.save(ConversationConverter.toPO(conversation));
    }

    @Override
    public boolean update(Conversation conversation) {
        return conversationPORepository.lambdaUpdate()
                .eq(ConversationPO::getConversationId, conversation.getId().value())
                .set(ConversationPO::getName, conversation.getName().value())
                .set(ConversationPO::getMetadata, conversation.getMetadata())
                .update();
    }

    @Override
    public boolean delete(ConversationId conversationId) {
        return conversationPORepository.lambdaUpdate()
                .eq(ConversationPO::getConversationId, conversationId.value())
                .remove();
    }

}
