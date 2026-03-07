package ai.neuron.copilot.knowledge.rag.app.service.conversation;

import ai.neuron.copilot.knowledge.rag.app.service.conversation.dify.DifyConversationImpl;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ConversationImplementerDispatcher {

    private final Map<KnowledgeBaseImpl, DifyConversationImpl> implementerMap;

    public ConversationImplementerDispatcher(List<DifyConversationImpl> implementerMap) {
        this.implementerMap = implementerMap.stream()
                .collect(Collectors.toUnmodifiableMap(
                        DifyConversationImpl::impl,
                        Function.identity()
                ));
    }

    public DifyConversationImpl get(KnowledgeBaseImpl impl) {
        DifyConversationImpl implementer = implementerMap.get(impl);
        if (implementer == null) {
            throw new IllegalArgumentException("Unsupported conversation impl: " + impl);
        }
        return implementer;
    }

}
