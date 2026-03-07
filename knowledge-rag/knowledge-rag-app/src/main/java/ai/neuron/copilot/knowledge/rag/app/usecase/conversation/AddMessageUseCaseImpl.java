package ai.neuron.copilot.knowledge.rag.app.usecase.conversation;

import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.AddMessageUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.AddMessageCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.dto.response.ConversationOutMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddMessageUseCaseImpl implements AddMessageUseCase {

    private final ConversationMessageSender conversationMessageSender;

    @Override
    @SneakyThrows
    @Async
    public void execute(AddMessageCommand command) {
        try {
            ConversationOutMessage asd = ConversationOutMessage.builder().data("asd").build();
            for (int i = 0; i < 10; i++) {
                conversationMessageSender.send(command.connectionId(), asd);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            conversationMessageSender.error(command.connectionId(), new SystemException(e));
        }
        conversationMessageSender.complete(command.connectionId());

    }

}
