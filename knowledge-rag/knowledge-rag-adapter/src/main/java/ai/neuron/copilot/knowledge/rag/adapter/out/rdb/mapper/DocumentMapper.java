package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mapper;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.DocumentPO;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;

public final class DocumentMapper {

    public static Document toDomain(DocumentPO po) {
        return Document.reconstitute(
                DocumentId.reconstitute(po.getDocumentId()),
                po.getOriginalFileName(),
                po.getDisplayName(),
                po.getExtension(),
                po.getObjectKey(),
                po.getBlobProvider(),
                TenantId.reconstitute(po.getTenantId())
        );
    }

    public static DocumentPO toPO(Document document) {
        DocumentPO po = new DocumentPO();
        po.setDocumentId(document.getId().value());
        po.setOriginalFileName(document.getOriginalFileName());
        po.setDisplayName(document.getDisplayName());
        po.setExtension(document.getExtension());
        po.setObjectKey(document.getObjectKey());
        po.setBlobProvider(document.getBlobProvider());
        po.setTenantId(document.getTenantId().value());
        return po;
    }

}