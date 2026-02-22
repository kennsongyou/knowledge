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
@TableName("knowledge_base")
public final class KnowledgeBasePO extends AuditPO {

	String knowledgeBaseId;

	String name;

	String description;

	String impl;

}
