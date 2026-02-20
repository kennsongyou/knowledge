package ai.neuron.copilot.knowledge.common.io;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;

@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InputStreamDTO {

    private final InputStream inputStream;

    private final String contentType;

    private final Long size;

}
