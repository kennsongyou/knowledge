package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.UpdateKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.UpdateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class UpdateKnowledgeBaseService implements UpdateKnowledgeBaseUseCase {

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	@Transactional
	@Override
	public void execute(UpdateKnowledgeBaseCommand command) {
		KnowledgeBase knowledgeBase = knowledgeBaseRepository.get(command.id(), command.tenantId());
		knowledgeBase.rename(command.name());
		knowledgeBase.changeDescription(command.description());
		knowledgeBaseRepository.save(knowledgeBase);
	}

}
