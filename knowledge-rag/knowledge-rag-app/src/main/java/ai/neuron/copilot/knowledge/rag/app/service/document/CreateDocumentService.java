package ai.neuron.copilot.knowledge.rag.app.service.document;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
import ai.neuron.copilot.knowledge.foundation.blob.BlobObject;
import ai.neuron.copilot.knowledge.foundation.blob.BlobObjectKey;
import ai.neuron.copilot.knowledge.foundation.blob.ObjectStorageClient;
import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentByFileCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateDocumentService implements CreateDocumentUseCase {

    private final ObjectStorageClient objectStorageClient;

    private final DocumentRepository documentRepository;

    @Override
    public DocumentId byFile(CreateDocumentByFileCommand command) {
        FileUploadDTO fileUploadDTO = command.fileUploadDTO();
        BlobObjectKey blobObjectKey = BlobObjectKey.create(objectStorageClient.keyPrefix(), fileUploadDTO.getExtension());
        BlobObject blobObject = BlobObject.create(blobObjectKey);
        objectStorageClient.save(command.inputStream(), fileUploadDTO, blobObject);
        DocumentId documentId = DocumentId.create();
        Document document = Document.create(
                documentId,
                fileUploadDTO.getOriginalFileName(),
                fileUploadDTO.getExtension(),
                blobObjectKey.value(),
                objectStorageClient.blobProvider().getType());
        boolean saved = documentRepository.save(document);
        if (!saved) {
            throw new SystemException();
        }
        return document.getId();
    }

}
