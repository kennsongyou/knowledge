package ai.neuron.copilot.knowledge.rag.app.service.conversation;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ConversationImplementerDispatcher {

    private final Map<KnowledgeBaseImpl, RagConversationImplementer> implementerMap;

    public ConversationImplementerDispatcher(List<RagConversationImplementer> implementerMap) {
        this.implementerMap = implementerMap.stream()
                .collect(Collectors.toUnmodifiableMap(
                        RagConversationImplementer::impl,
                        Function.identity()
                ));
    }

    public RagConversationImplementer get(KnowledgeBaseImpl impl) {
        RagConversationImplementer implementer = implementerMap.get(impl);
        if (implementer == null) {
            throw new IllegalArgumentException("Unsupported knowledge base impl: " + impl);
        }
        return implementer;
    }

}
