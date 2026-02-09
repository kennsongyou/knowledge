package ai.neuron.copilot.knowledge.rag.app.service.document;

import ai.neuron.copilot.knowledge.rag.app.port.in.document.GetDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.blob.ObjectStorageClient;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.BlobObject;
import ai.neuron.copilot.knowledge.rag.domain.document.model.BlobObjectKey;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;

@RequiredArgsConstructor
@Service
public class GetDocumentService implements GetDocumentUseCase {

    private final DocumentRepository documentRepository;

    private final ObjectStorageClient objectStorageClient;

    @Override
    public Document execute(GetDocumentQuery query) {
        Document document = documentRepository.get(query.id(), query.tenantId());
        BlobObjectKey objectKey = BlobObjectKey.reconstitute(document.getObjectKey());
        URL url = objectStorageClient.url(objectKey);
        document.setUrl(url.toString());
        return document;
    }

}
