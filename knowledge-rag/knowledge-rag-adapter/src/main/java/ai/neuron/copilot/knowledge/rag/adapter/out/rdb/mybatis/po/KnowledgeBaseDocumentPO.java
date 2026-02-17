package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po;

import ai.neuron.copilot.knowledge.foundation.data.rdb.jpa.CreatedAuditingPO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class KnowledgeBaseDocumentPO extends CreatedAuditingPO {

	private String knowledgeBaseId;

	private String documentId;

}
