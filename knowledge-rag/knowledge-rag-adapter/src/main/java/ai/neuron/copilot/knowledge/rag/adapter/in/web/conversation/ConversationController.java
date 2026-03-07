package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.foundation.data.page.CursorQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.CursorResult;
import ai.neuron.copilot.knowledge.foundation.web.sse.server.SseServerManager;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.request.CreateMessageRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.request.CursorConversationRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared.ConversationDTO;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared.ConversationPayloadDTO;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared.ConversationKbqaPayloadDTO;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.CreateMessageUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.CursorConversationUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.DeleteConversationUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.CreateMessageCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.DeleteConversationCommand;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationAbility;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private final SseServerManager sseServerManager;

    private final CreateMessageUseCase createMessageUseCase;

    private final CursorConversationUseCase cursorConversationUseCase;

    private final DeleteConversationUseCase deleteConversationUseCase;

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SseEmitter createMessage(@RequestBody @Valid CreateMessageRequest request) {
        ConversationPayloadDTO payload = request.getPayload();
        Optional.ofNullable(payload).map(ConversationPayloadDTO::getAbility)
                .filter(e -> StringUtils.equals(e, ConversationAbility.KBQA.getAbility()))
                .orElseThrow(() -> new UnsupportedOperationException("unsupported conversation ability"));
        ConversationKbqaPayloadDTO kbqaPayload = (ConversationKbqaPayloadDTO) request.getPayload();

        String serverId = IdUtils.uuidV4Str();
        SseEmitter emitter = sseServerManager.register(serverId);
        KnowledgeBaseId knowledgeBaseId = KnowledgeBaseId.reconstitute(kbqaPayload.getKnowledgeBaseId());
        ConversationId conversationId = Optional.ofNullable(request.getConversationId())
                .filter(StringUtils::isNotBlank)
                .map(ConversationId::reconstitute)
                .orElse(null);
        CreateMessageCommand command = new CreateMessageCommand(serverId, conversationId, knowledgeBaseId,
                request.getQuery());
        createMessageUseCase.execute(command);
        return emitter;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CursorResult<ConversationDTO> cursorConversations(@ModelAttribute CursorConversationRequest request) {
        CursorQuery query = new CursorQuery(request.getCursor(), request.getLimit());
        CursorResult<Conversation> cursorResult = cursorConversationUseCase.execute(query);
        List<ConversationDTO> records = cursorResult.records().stream().map(conversation -> new ConversationDTO(
                conversation.getId().value(),
                conversation.getName().value()
        )).toList();
        return new CursorResult<>(records, cursorResult.nextCursor());
    }

    @DeleteMapping("/{conversation_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConversation(@PathVariable("conversation_id") String conversationId) {
        deleteConversationUseCase.execute(new DeleteConversationCommand(ConversationId.reconstitute(conversationId)));
    }

}
