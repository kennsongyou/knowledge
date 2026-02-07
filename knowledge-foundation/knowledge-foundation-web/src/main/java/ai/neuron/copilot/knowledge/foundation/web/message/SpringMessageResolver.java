package ai.neuron.copilot.knowledge.foundation.web.message;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class SpringMessageResolver implements MessageResolver {

    private final MessageSource messageSource;

    @Override
    public String resolve(@Nonnull String messageKey, Locale locale, Object... args) {
        locale = locale == null ? Locale.ROOT : locale;
        return messageSource.getMessage(messageKey, args, messageKey, locale);
    }

}
