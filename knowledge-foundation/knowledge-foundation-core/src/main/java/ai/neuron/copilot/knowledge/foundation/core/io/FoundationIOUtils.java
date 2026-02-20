package ai.neuron.copilot.knowledge.foundation.core.io;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

public final class FoundationIOUtils {

    public static Resource toResource(InputStream inputStream, String fileName, long size) {
        return new InputStreamResource(inputStream) {
            @Override
            public String getFilename() {
                return fileName;
            }
            @Override
            public long contentLength() {
                return size;
            }
        };
    }

}
