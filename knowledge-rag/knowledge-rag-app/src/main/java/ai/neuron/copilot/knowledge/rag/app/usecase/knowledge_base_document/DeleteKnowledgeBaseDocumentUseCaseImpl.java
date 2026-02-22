package ai.neuron.copilot.knowledge.rag.app.usecase.knowledge_base_document;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.DeleteKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.DeleteKnowledgeBaseDocumentCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseDocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.service.knowledge_base.KnowledgeBaseImplementerDispatcher;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDocument;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class DeleteKnowledgeBaseDocumentUseCaseImpl implements DeleteKnowledgeBaseDocumentUseCase {

	private final KnowledgeBaseDocumentRepository knowledgeBaseDocumentRepository;

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	private final DocumentRepository documentRepository;

	private final KnowledgeBaseImplementerDispatcher knowledgeBaseImplementerDispatcher;

	@Override
	public void execute(DeleteKnowledgeBaseDocumentCommand command) {
		KnowledgeBase knowledgeBase = knowledgeBaseRepository.fetch(command.knowledgeBaseId())
				.orElseThrow(ResourceNotFoundException::new);
		Document document = documentRepository.fetch(command.documentId()).orElseThrow(ResourceNotFoundException::new);
		boolean deleted = knowledgeBaseDocumentRepository.delete(command.knowledgeBaseId(), command.documentId());
		if (!deleted) {
			throw new ResourceNotFoundException();
		}
		knowledgeBaseImplementerDispatcher.get(knowledgeBase.getImpl()).deleteDocument(knowledgeBase, document);

	}

}
