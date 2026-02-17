package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DocumentPO;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;

public final class DocumentConverter {

    public static Document toDomain(DocumentPO po) {
        return Document.reconstitute(
                DocumentId.reconstitute(po.getDocumentId()),
                po.getOriginalFileName(),
                po.getDisplayName(),
                po.getExtension(),
                po.getObjectKey(),
                po.getBlobProvider());
    }

    public static DocumentPO toPO(Document document) {
        DocumentPO po = new DocumentPO();
        po.setDocumentId(document.getId().value());
        po.setOriginalFileName(document.getOriginalFileName());
        po.setDisplayName(document.getDisplayName());
        po.setExtension(document.getExtension());
        po.setObjectKey(document.getObjectKey());
        po.setBlobProvider(document.getBlobProvider());
        return po;
    }

}