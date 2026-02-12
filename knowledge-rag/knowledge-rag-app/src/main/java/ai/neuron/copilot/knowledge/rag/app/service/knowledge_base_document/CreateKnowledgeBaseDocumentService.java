package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base_document;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.CreateKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.CreateKnowledgeBaseDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseDocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CreateKnowledgeBaseDocumentService implements CreateKnowledgeBaseDocumentUseCase {

	private final KnowledgeBaseDocumentRepository knowledgeBaseDocumentRepository;

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	private final DocumentRepository documentRepository;

	@Override
	public void execute(CreateKnowledgeBaseDocumentCommand command) {
		knowledgeBaseRepository.fetch(command.knowledgeBaseId()).orElseThrow(ResourceNotFoundException::new);;
		documentRepository.fetch(command.documentId()).orElseThrow(ResourceNotFoundException::new);;
		knowledgeBaseDocumentRepository.save(command.knowledgeBaseId(), command.documentId());
	}

}
