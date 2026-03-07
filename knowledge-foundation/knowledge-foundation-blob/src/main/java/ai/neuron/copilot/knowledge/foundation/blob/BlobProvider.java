package ai.neuron.copilot.knowledge.foundation.blob;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BlobProvider {

    LOCAL("local"),

    QCLOUD("qcloud");

    private final String type;

}
