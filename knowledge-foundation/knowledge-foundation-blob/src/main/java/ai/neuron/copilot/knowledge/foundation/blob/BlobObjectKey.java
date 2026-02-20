package ai.neuron.copilot.knowledge.foundation.blob;

import ai.neuron.copilot.knowledge.common.util.IdUtils;

import java.util.Objects;

public record BlobObjectKey(String value) {

    public BlobObjectKey {
        Objects.requireNonNull(value, "Blob Object Key cannot be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Blob Object Key cannot be empty");
        }
    }

    public static BlobObjectKey reconstitute(String key) {
        return new BlobObjectKey(key);
    }

    public static BlobObjectKey create(String prefix, String ext) {
        return new BlobObjectKey(prefix + "/" + IdUtils.uuidV4Str() + "." + ext);
    }

    public String fileName() {
        int slashIndex = value.lastIndexOf('/');
        return slashIndex >= 0 ? value.substring(slashIndex + 1) : value;
    }

    public String extension() {
        String filename = fileName();
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex < 0 || dotIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(dotIndex + 1);
    }

    @Override
    public String toString() {
        return value;
    }

}
