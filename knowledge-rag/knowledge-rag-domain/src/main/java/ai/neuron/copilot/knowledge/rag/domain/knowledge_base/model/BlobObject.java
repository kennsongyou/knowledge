package ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model;

import java.time.Instant;
import java.util.Objects;

public record BlobObject(BlobObjectKey blobObjectKey, Instant expiration) {

    public BlobObject {
        Objects.requireNonNull(blobObjectKey, "Blob Object Key cannot be null");
    }

    public static BlobObject create(BlobObjectKey blobObjectKey) {
        return create(blobObjectKey, null);
    }

    public static BlobObject create(BlobObjectKey blobObjectKey, Instant expiration) {
        return new BlobObject(blobObjectKey, expiration);
    }

}
