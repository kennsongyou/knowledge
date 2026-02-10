package ai.neuron.copilot.knowledge.foundation.blob;

import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;


public interface ObjectStorageClient {

    void save(InputStream inputStream, FileUploadDTO fileUploadDTO, BlobObject blobObject);

    URL url(BlobObjectKey blobObjectKey, Duration timeout);

    String keyPrefix();

    BlobProvider blobProvider();

}
