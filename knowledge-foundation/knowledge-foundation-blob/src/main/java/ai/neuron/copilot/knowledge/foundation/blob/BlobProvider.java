package ai.neuron.copilot.knowledge.foundation.blob;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BlobProvider {

    QCLOUD("qcloud");

    private final String type;

}
