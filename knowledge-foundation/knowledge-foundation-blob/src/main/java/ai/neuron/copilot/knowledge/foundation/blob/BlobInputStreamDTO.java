package ai.neuron.copilot.knowledge.foundation.blob;

import ai.neuron.copilot.knowledge.common.io.InputStreamDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlobInputStreamDTO extends InputStreamDTO {

    final String objectKey;

    final BlobProvider blobProvider;

}
