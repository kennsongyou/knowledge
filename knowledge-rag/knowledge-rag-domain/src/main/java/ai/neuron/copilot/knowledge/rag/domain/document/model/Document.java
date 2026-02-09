package ai.neuron.copilot.knowledge.rag.domain.document.model;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class Document {

    final DocumentId id;

    final String originalFileName;

    String displayName;

    final String extension;

    final String objectKey;

    final String blobProvider;

    final TenantId tenantId;

    @Setter(AccessLevel.PUBLIC)
    String url;

    private Document(DocumentId id, String originalFileName, String displayName, String extension, String objectKey,
                     String blobProvider, TenantId tenantId) {
        this.id = Objects.requireNonNull(id);
        this.originalFileName = requireValidName(originalFileName);
        this.displayName = requireValidName(displayName);
        this.extension = extension;
        this.objectKey = objectKey;
        this.blobProvider = blobProvider;
        this.tenantId = tenantId;
    }

    public void rename(String displayName) {
        this.displayName = requireValidName(displayName);
    }

    public static Document reconstitute(DocumentId id, String originalFileName, String displayName, String extension,
                                        String objectKey, String blobProvider, TenantId tenantId) {
        return new Document(id, originalFileName, displayName, extension, objectKey, blobProvider, tenantId);
    }

    public static Document create(DocumentId id, String originalFileName, String extension, String objectKey,
                                  String blobProvider, TenantId tenantId) {
        return new Document(id, originalFileName, originalFileName, extension, objectKey, blobProvider, tenantId);
    }

    private static String requireValidName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Document name cannot be empty");
        }
        if (StringUtils.length(name) > 255) {
            throw new IllegalArgumentException("Document name is too long (max 255 characters)");
        }
        return name;
    }

}
