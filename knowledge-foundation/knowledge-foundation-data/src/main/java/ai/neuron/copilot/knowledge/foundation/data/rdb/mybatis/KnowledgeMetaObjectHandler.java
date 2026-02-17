package ai.neuron.copilot.knowledge.foundation.data.rdb.mybatis;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.TenantContext;
import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.Instant;
import java.util.Optional;

public class KnowledgeMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Optional.ofNullable(ContextHolder.tenant()).map(TenantContext::id).ifPresent(tenantId -> {
            this.strictInsertFill(metaObject, "tenantId", Long.class, tenantId);
        });
        Optional.ofNullable(ContextHolder.user()).map(UserContext::id).ifPresent(userId -> {
            this.strictInsertFill(metaObject, "createdBy", Long.class, userId.value());
            this.strictInsertFill(metaObject, "updatedBy", Long.class, userId.value());
        });
        Instant now = Instant.now();
        this.strictInsertFill(metaObject, "createdAt", Instant.class, now);
        this.strictInsertFill(metaObject, "updatedAt", Instant.class, now);
        this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Optional.ofNullable(ContextHolder.user()).map(UserContext::id).ifPresent(userId -> {
            this.strictInsertFill(metaObject, "updatedBy", Long.class, userId.value());
        });
        Instant now = Instant.now();
        this.strictUpdateFill(metaObject, "updatedAt", Instant.class, now);
    }

}
