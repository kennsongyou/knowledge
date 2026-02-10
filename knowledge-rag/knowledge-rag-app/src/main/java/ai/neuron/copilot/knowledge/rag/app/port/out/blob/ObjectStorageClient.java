//package ai.neuron.copilot.knowledge.rag.app.port.out.blob;
//
//import ai.neuron.copilot.knowledge.common.io.FileUploadDTO;
//import ai.neuron.copilot.knowledge.rag.domain.document.model.BlobObject;
//import ai.neuron.copilot.knowledge.rag.domain.document.model.BlobObjectKey;
//
//import java.io.InputStream;
//import java.net.URL;
//import java.time.Duration;
//
//
//public interface ObjectStorageClient {
//
//    void save(InputStream inputStream, FileUploadDTO fileUploadDTO, BlobObject blobObject);
//
//    URL url(BlobObjectKey blobObjectKey, Duration timeout);
//
//    String keyPrefix();
//
//    String blobProvider();
//
//}
