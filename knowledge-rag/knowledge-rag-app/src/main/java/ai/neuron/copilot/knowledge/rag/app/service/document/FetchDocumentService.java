package ai.neuron.copilot.knowledge.rag.app.service.document;

import ai.neuron.copilot.knowledge.foundation.blob.BlobObjectKey;
import ai.neuron.copilot.knowledge.foundation.blob.ObjectStorageClient;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.FetchDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.GetDocumentQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.query.FetchDocumentUrlQuery;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.Duration;

@RequiredArgsConstructor
@Service
public class FetchDocumentService implements FetchDocumentUseCase {

    private final DocumentRepository documentRepository;

    private final ObjectStorageClient objectStorageClient;

    @Override
    public Document execute(GetDocumentQuery query) {
        return documentRepository.fetch(query.id(), query.tenantId());
    }

    @Override
    public String accessUrl(FetchDocumentUrlQuery query) {
        Document document = documentRepository.fetch(query.id(), query.tenantId());
        BlobObjectKey objectKey = BlobObjectKey.reconstitute(document.getObjectKey());
        URL url = objectStorageClient.url(objectKey, Duration.ofMinutes(1));
        return url.toString();
    }

}
