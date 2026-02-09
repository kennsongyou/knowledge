package ai.neuron.copilot.knowledge.common.io;

import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
public class InputStreamDTO {

    private final InputStream inputStream;
    private final String contentType;
    private final Long size;

    public InputStreamDTO(InputStream inputStream, String contentType, Long size) {
        this.inputStream = inputStream;
        this.contentType = contentType;
        this.size = size;
    }

}
