package ai.neuron.copilot.knowledge.rag.app.service.document;

import ai.neuron.copilot.knowledge.rag.app.port.in.document.GetDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetDocumentService implements GetDocumentUseCase {

    private final DocumentRepository documentRepository;

    @Override
    public Document execute(GetDocumentQuery query) {
        return documentRepository.get(query.id(), query.tenantId());
    }

}
