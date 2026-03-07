package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.ConversationPO;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationName;

public final class ConversationConverter {

    public static Conversation toDomain(ConversationPO po) {
        return Conversation.reconstitute(
                ConversationId.reconstitute(po.getConversationId()),
                ConversationName.reconstitute(po.getName()),
                po.getMetadata()
        );
    }

    public static ConversationPO toPO(Conversation conversation) {
        ConversationPO po = new ConversationPO();
        po.setConversationId(conversation.getId().value());
        po.setName(conversation.getName().value());
        po.setMetadata(conversation.getMetadata());
        return po;
    }


}
