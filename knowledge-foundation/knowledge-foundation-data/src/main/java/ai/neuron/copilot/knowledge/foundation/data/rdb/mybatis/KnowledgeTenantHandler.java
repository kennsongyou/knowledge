package ai.neuron.copilot.knowledge.foundation.data.rdb.mybatis;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.TenantContext;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class KnowledgeTenantHandler implements TenantLineHandler {

    private static final KnowledgeTenantHandler INSTANCE = new KnowledgeTenantHandler();

    public static KnowledgeTenantHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public Expression getTenantId() {
        Long tenantId = Optional.ofNullable(ContextHolder.tenant())
                .map(TenantContext::id)
                .orElse(0L);
        return new LongValue(tenantId);
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return StringUtils.startsWithIgnoreCase(tableName, "sys_");
    }

}
