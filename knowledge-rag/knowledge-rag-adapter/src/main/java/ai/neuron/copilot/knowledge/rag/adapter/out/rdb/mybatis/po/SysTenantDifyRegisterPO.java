package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@TableName(value = "sys_tenant_dify_register", autoResultMap = true)
public final class SysTenantDifyRegisterPO {

	@TableId(type = IdType.AUTO)
	Long id;

	String sysTenantDifyRegisterId;

	Long tenantId;

	String datasetId;

	@TableField(typeHandler = JacksonTypeHandler.class)
	Map<String, String> datasetMetadata;

	String appId;

	String appApiKey;

	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	String deleted;

	@TableField(fill = FieldFill.INSERT)
	Instant createdAt;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	Instant updatedAt;

}
