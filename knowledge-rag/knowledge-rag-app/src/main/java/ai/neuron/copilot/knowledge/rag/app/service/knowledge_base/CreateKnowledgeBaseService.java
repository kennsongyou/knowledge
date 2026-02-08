package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.CreateKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.CreateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.config.DifyDatasetIdProvider;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDatasetId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class CreateKnowledgeBaseService implements CreateKnowledgeBaseUseCase {

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	private final DifyDatasetIdProvider difyDatasetIdProvider;

	@Transactional
	@Override
	public KnowledgeBaseId execute(CreateKnowledgeBaseCommand command) {
		KnowledgeBase knowledgeBase = KnowledgeBase.create(command.name(), command.description(),
				difyDatasetIdProvider.get());
		knowledgeBaseRepository.create(knowledgeBase);
		return knowledgeBase.getId();
	}

}
