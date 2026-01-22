package ai.neuron.copilot.knowledge.foundation.core.logging;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.RequestContext;
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class TraceIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return Optional.ofNullable(ContextHolder.request()).map(RequestContext::traceId).orElse(StringUtils.EMPTY);
    }
}
