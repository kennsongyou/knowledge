package ai.neuron.copilot.knowledge.foundation.blob;

import java.io.InputStream;

public interface ObjectStorageClient {

    void save(String key, InputStream inputStream);

    InputStream get(String key);

}
