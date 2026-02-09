package ai.neuron.copilot.knowledge.rag.app.port.out.blob;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
import ai.neuron.copilot.knowledge.rag.domain.document.model.BlobObject;

import java.io.InputStream;
import java.net.URL;


public interface ObjectStorageClient {

    void save(InputStream inputStream, FileUploadDTO fileUploadDTO, BlobObject blobObject);

    URL url(BlobObject blobObject);

    String keyPrefix();

    String blobProvider();

}
