package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import ai.neuron.copilot.knowledge.common.util.IdUtils;

import java.util.Objects;

public record BlobObjectKey(String value) {

    public BlobObjectKey {
        Objects.requireNonNull(value, "Blob Object Key cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Blob Object Key cannot be empty");
        }
    }

    public static BlobObjectKey reconstitute(String prefix, String key) {
        return new BlobObjectKey(prefix + "/" + key);
    }

    public static BlobObjectKey create(String prefix, String ext) {
        return new BlobObjectKey(prefix + "/" + IdUtils.uuid() + "." + ext);
    }

    @Override
    public String toString() {
        return value;
    }

}
