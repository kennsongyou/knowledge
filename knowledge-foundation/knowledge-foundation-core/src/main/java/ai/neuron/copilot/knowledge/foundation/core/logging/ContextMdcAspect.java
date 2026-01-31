package ai.neuron.copilot.knowledge.foundation.core.logging;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.RequestContext;
import ai.neuron.copilot.knowledge.foundation.core.context.TenantContext;
import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Aspect
@Configuration
public class ContextMdcAspect {

    @Pointcut("execution(* ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder.setContextContainer(*))")
    public void setContextContainerPointcut() {
    }

    @Pointcut("execution(* ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder.clear())")
    public void clearPointcut() {
    }

    @AfterReturning("setContextContainerPointcut()")
    public void afterSetContextContainer() {
        MdcUtil.put(LogConstants.REQUEST_ID, Optional.ofNullable(ContextHolder.request()).map(RequestContext::requestId)
                .orElse(LogConstants.UNKNOWN));
        MdcUtil.put(LogConstants.TRACE_ID, Optional.ofNullable(ContextHolder.request()).map(RequestContext::traceId)
                .orElse(LogConstants.UNKNOWN));
        MdcUtil.put(LogConstants.USER_ID, Optional.ofNullable(ContextHolder.user()).map(UserContext::id)
                .map(Object::toString).orElse(LogConstants.UNKNOWN));
        MdcUtil.put(LogConstants.TENANT_ID, Optional.ofNullable(ContextHolder.tenant()).map(TenantContext::id)
                .map(Object::toString).orElse(LogConstants.UNKNOWN));
    }

    @Before("clearPointcut()")
    public void beforeClear() {
        MdcUtil.clear();
    }
}
