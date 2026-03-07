package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify;

import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.foundation.core.json.SnakeCaseJsonCodec;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.ConversationImplementer;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.DifyConversationMetadata;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@AllArgsConstructor
public class DifyConversationImpl implements ConversationImplementer {

    private final SnakeCaseJsonCodec jsonCodec;

    @Override
    public void start(boolean firstMessage, Conversation conversation, String sseServerId,
                      Function<Conversation, Boolean> updateConversationFunc) {
        DifyConversationMetadata metadata = null;
        String difyConversationId = null;
        if (!firstMessage) {
            metadata = jsonCodec.decode(conversation.getMetadata(), DifyConversationMetadata.class);
            difyConversationId = Optional.ofNullable(metadata).map(DifyConversationMetadata::difyConversationId)
                    .orElseThrow(() -> {
                        log.error("metadata is null, cannot get difyConversationId");
                        return new SystemException();
                    });
        }

    }

    @Override
    public KnowledgeBaseImpl impl() {
        return KnowledgeBaseImpl.DIFY;
    }

}
