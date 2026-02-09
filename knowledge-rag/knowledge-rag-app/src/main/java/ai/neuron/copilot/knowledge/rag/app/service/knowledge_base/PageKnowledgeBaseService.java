package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.PageKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query.PageKnowledgeBaseQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PageKnowledgeBaseService implements PageKnowledgeBaseUseCase {

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    @Override
    public PageResult<KnowledgeBase> execute(PageKnowledgeBaseQuery query) {
        return knowledgeBaseRepository.pageByKeyword(query.keyword(), query.pageQuery(), query.tenantId());
    }
}
