package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.ConversationPO;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationName;

public final class ConversationConverter {

    public static Conversation toDomain(ConversationPO po) {
        return Conversation.reconstitute(
                ConversationId.reconstitute(po.getConversationId()),
                ConversationName.reconstitute(po.getName())
        );
    }

    public static ConversationPO toPO(Conversation conversation) {
        ConversationPO po = new ConversationPO();
        po.setConversationId(conversation.id().value());
        po.setName(conversation.name().value());
        return po;
    }


}
