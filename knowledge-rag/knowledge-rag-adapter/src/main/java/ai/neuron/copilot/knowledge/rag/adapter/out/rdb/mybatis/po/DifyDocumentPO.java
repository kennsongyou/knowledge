package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po;

import ai.neuron.copilot.knowledge.foundation.data.rdb.mybatis.AuditPO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@TableName("dify_document")
public final class DifyDocumentPO extends AuditPO {

	String difyDocumentId;

	String documentId;

	String externalDocumentId;

}
