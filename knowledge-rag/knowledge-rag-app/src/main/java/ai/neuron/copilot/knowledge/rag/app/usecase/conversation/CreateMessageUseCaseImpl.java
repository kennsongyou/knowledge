package ai.neuron.copilot.knowledge.rag.app.usecase.conversation;

import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.CreateMessageUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.CreateMessageCommand;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.conversation.ConversationMessageSender;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.ConversationRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.ConversationImplementerDispatcher;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dto.ChatStartDTO;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateMessageUseCaseImpl implements CreateMessageUseCase {

    private final ConversationMessageSender conversationMessageSender;

    private final ConversationImplementerDispatcher conversationImplementerDispatcher;

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    private final ConversationRepository conversationRepository;

    @Override
    public void execute(CreateMessageCommand command) {
        KnowledgeBase knowledgeBase = knowledgeBaseRepository.fetch(command.knowledgeBaseId())
                .orElseThrow(ResourceNotFoundException::new);
        ConversationId conversationId = command.conversationId();
        Conversation conversation;
        if (conversationId == null) {
            conversation = Conversation.create();
            conversationRepository.save(conversation);
        } else {
            conversation = conversationRepository.fetch(conversationId)
                    .orElseThrow(ResourceNotFoundException::new);
        }
        KnowledgeBaseImpl impl = knowledgeBase.getImpl();
        ChatStartDTO chatStartDTO = new ChatStartDTO(conversationId == null, conversation,
                knowledgeBase, command.sseServerId(), command.query());
        conversationImplementerDispatcher.get(impl).start(chatStartDTO);
    }

}
