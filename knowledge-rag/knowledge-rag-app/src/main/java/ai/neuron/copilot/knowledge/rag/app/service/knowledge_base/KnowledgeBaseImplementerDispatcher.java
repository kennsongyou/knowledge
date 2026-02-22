package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class KnowledgeBaseImplementerDispatcher {

    private final Map<KnowledgeBaseImpl, KnowledgeBaseImplementer> implementerMap;

    public KnowledgeBaseImplementerDispatcher(List<KnowledgeBaseImplementer> implementerMap) {
        this.implementerMap = implementerMap.stream()
                .collect(Collectors.toUnmodifiableMap(
                        KnowledgeBaseImplementer::impl,
                        Function.identity()
                ));
    }

    public KnowledgeBaseImplementer get(KnowledgeBaseImpl impl) {
        KnowledgeBaseImplementer implementer = implementerMap.get(impl);
        if (implementer == null) {
            throw new IllegalArgumentException("Unsupported knowledge base impl: " + impl);
        }
        return implementer;
    }
}
