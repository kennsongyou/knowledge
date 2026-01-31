package ai.neuron.copilot.knowledge.foundation.core.logging;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.RequestContext;
import ai.neuron.copilot.knowledge.foundation.core.context.TenantContext;
import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;

import java.util.Optional;

public final class ContextMdcIntegrator {

    private ContextMdcIntegrator() {
    }

    public static void integrate() {
        MdcUtil.put(LogConstants.REQUEST_ID, Optional.ofNullable(ContextHolder.request()).map(RequestContext::requestId)
                .orElse(LogConstants.UNKNOWN));
        MdcUtil.put(LogConstants.TRACE_ID, Optional.ofNullable(ContextHolder.request()).map(RequestContext::traceId)
                .orElse(LogConstants.UNKNOWN));
        MdcUtil.put(LogConstants.USER_ID, Optional.ofNullable(ContextHolder.user()).map(UserContext::id)
                .map(Object::toString).orElse(LogConstants.UNKNOWN));
        MdcUtil.put(LogConstants.TENANT_ID, Optional.ofNullable(ContextHolder.tenant()).map(TenantContext::id)
                .map(Object::toString).orElse(LogConstants.UNKNOWN));
    }

    public static void clear() {
        MdcUtil.clear();
    }

}
