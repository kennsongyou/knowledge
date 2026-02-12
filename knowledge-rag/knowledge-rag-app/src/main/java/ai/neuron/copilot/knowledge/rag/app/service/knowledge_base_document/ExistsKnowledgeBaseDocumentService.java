package ai.neuron.copilot.knowledge.rag.app.service.knowledge_base_document;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.ExistsKnowledgeBaseDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query.ExistsKnowledgeBaseDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseDocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ExistsKnowledgeBaseDocumentService implements ExistsKnowledgeBaseDocumentUseCase {

	private final KnowledgeBaseDocumentRepository knowledgeBaseDocumentRepository;

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	private final DocumentRepository documentRepository;

	@Override
	public void requireRelationExists(ExistsKnowledgeBaseDocumentQuery query) {
		knowledgeBaseRepository.fetch(query.knowledgeBaseId()).orElseThrow(ResourceNotFoundException::new);
		documentRepository.fetch(query.documentId()).orElseThrow(ResourceNotFoundException::new);
		boolean exists = knowledgeBaseDocumentRepository.exists(query.knowledgeBaseId(), query.documentId());
		if (!exists) {
			throw new ResourceNotFoundException();
		}
	}

}
