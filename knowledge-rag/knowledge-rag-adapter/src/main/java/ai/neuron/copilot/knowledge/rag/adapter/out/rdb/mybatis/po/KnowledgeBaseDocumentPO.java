package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po;

import ai.neuron.copilot.knowledge.foundation.data.rdb.mybatis.CreatedAuditPO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@TableName("knowledge_base_document")
public final class KnowledgeBaseDocumentPO extends CreatedAuditPO {

	String knowledgeBaseId;

	String documentId;

}
