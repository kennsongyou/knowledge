package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.GetKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query.GetKnowledgeBaseQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetKnowledgeBaseService implements GetKnowledgeBaseUseCase {

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    @Override
    public KnowledgeBase execute(GetKnowledgeBaseQuery query) {
        return knowledgeBaseRepository.get(query.id(), query.tenantId());
    }

}
