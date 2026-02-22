package ai.neuron.copilot.knowledge.rag.app.usecase.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceAlreadyExistException;
import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.CreateKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.CreateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.service.knowledge_base.KnowledgeBaseImplementerDispatcher;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class CreateKnowledgeBaseUseCaseImpl implements CreateKnowledgeBaseUseCase {

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	private final KnowledgeBaseImplementerDispatcher knowledgeBaseImplementerDispatcher;

	@Transactional
	@Override
	public KnowledgeBaseId execute(CreateKnowledgeBaseCommand command) {
		knowledgeBaseRepository.getByName(command.name())
				.ifPresent(knowledgeBase -> { throw new ResourceAlreadyExistException(); });
		KnowledgeBase knowledgeBase = KnowledgeBase.create(
				command.name(),
				command.description(),
				command.impl()
		);
		boolean saved = knowledgeBaseRepository.save(knowledgeBase);
		if (!saved) {
			throw new SystemException();
		}
		knowledgeBaseImplementerDispatcher.get(knowledgeBase.getImpl()).create(knowledgeBase);
		return knowledgeBase.getId();
	}

}
