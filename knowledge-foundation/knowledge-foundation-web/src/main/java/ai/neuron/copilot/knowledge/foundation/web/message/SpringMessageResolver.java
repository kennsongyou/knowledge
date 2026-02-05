package ai.neuron.copilot.knowledge.foundation.web.message;

import jakarta.annotation.Nonnull;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SpringMessageResolver implements MessageResolver {

    private final MessageSource messageSource;

    public SpringMessageResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String resolve(@Nonnull String messageKey, Locale locale, Object... args) {
        if (locale == null) {
            locale = Locale.ROOT;
        }
        return messageSource.getMessage(messageKey, args, messageKey, locale);
    }

}
