package ai.neuron.copilot.knowledge.rag.app.usecase.conversation;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.CreateMessageUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.CreateMessageCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.ConversationRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.ConversationImplementerDispatcher;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CreateMessageUseCaseImpl implements CreateMessageUseCase {

    private final ConversationMessageSender conversationMessageSender;

    private final ConversationImplementerDispatcher conversationImplementerDispatcher;

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    private final ConversationRepository conversationRepository;

    @Override
    @Async
    public void execute(CreateMessageCommand command) {
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.fetch(command.knowledgeBaseId())
                .orElseThrow(ResourceNotFoundException::new);
        KnowledgeBaseImpl impl = knowledgeBase.getImpl();
        ConversationId conversationId = command.conversationId();
        Conversation conversation = Optional.ofNullable(conversationId)
                .map(e -> conversationRepository.fetch(e).orElseThrow(ResourceNotFoundException::new))
                .orElse(Conversation.create());
        conversationRepository.save(conversation);
        conversationImplementerDispatcher.get(impl).start(conversationId == null, conversation,
                command.sseServerId(), conversationRepository::update);
    }

}
