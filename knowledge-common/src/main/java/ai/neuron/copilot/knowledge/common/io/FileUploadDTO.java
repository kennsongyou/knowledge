package ai.neuron.copilot.knowledge.common.io;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileUploadDTO {

    String originalFileName;

    String baseName;

    String extension;

    String contentType;

    Long size;

}
