package ai.neuron.copilot.knowledge.foundation.web.message;

import java.util.Locale;

public interface MessageResolver {

    String resolve(String messageKey, Locale locale, Object... args);

}
