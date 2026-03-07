package ai.neuron.copilot.knowledge.rag.app.service.conversation.impl;

import ai.neuron.copilot.knowledge.rag.app.service.conversation.RagConversationImplementer;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DifyRagConversationImplementer implements RagConversationImplementer {

    @Override
    public KnowledgeBaseImpl impl() {
        return KnowledgeBaseImpl.DIFY;
    }

}
