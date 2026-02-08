package ai.neuron.copilot.knowledge.common.io;

import java.io.InputStream;
import java.util.OptionalLong;

public record InputStreamDTO(
        InputStream inputStream,
        String contentType,
        OptionalLong size) {
}
