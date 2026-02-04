package ai.neuron.copilot.knowledge.foundation.core.message;

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
    public String resolve(String messageKey, Locale locale, Object... args) {
        if (messageKey == null) {
            return null;
        }
        if (locale == null) {
            locale = Locale.ROOT;
        }
        return messageSource.getMessage(messageKey, args, messageKey, locale);
    }

}
