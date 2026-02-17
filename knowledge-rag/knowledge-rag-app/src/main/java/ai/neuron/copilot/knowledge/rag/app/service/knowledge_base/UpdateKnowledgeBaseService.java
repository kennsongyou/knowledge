package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceAlreadyExistException;
import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.UpdateKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.UpdateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UpdateKnowledgeBaseService implements UpdateKnowledgeBaseUseCase {

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	@Transactional
	@Override
	public void execute(UpdateKnowledgeBaseCommand command) {
		Optional<KnowledgeBase> byName = knowledgeBaseRepository.getByName(command.name());
		byName.filter(e -> !e.getId().equals(command.id())).ifPresent(e -> {
			throw new ResourceAlreadyExistException();
		});
		KnowledgeBase knowledgeBase = knowledgeBaseRepository.fetch(command.id())
				.orElseThrow(ResourceNotFoundException::new);
		knowledgeBase.rename(command.name());
		knowledgeBase.changeDescription(command.description());
		boolean updated = knowledgeBaseRepository.update(knowledgeBase);
		if (!updated) {
			throw new SystemException();
		}
	}

}
