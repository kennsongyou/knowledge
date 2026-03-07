package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.foundation.data.page.CursorQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.CursorResult;
import ai.neuron.copilot.knowledge.foundation.web.sse.server.SseServerManager;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.request.AddMessageRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.request.CursorConversationRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.shared.ConversationDTO;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.AddMessageUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.CursorConversationUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.DeleteConversationUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.AddMessageCommand;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private final SseServerManager registry;

    private final AddMessageUseCase addMessageUseCase;

    private final CursorConversationUseCase cursorConversationUseCase;

    private final DeleteConversationUseCase deleteConversationUseCase;

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SseEmitter> addMessage(@RequestBody @Valid AddMessageRequest request) {
        String connectionId = IdUtils.trimmedUuidV4();
        try {
            SseEmitter emitter = registry.register(connectionId);
            AddMessageCommand command = new AddMessageCommand("asd", connectionId, "qwe");
            addMessageUseCase.execute(command);
            return ResponseEntity.ok(emitter);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CursorResult<ConversationDTO> cursorConversations(@ModelAttribute CursorConversationRequest request) {
        CursorQuery query = new CursorQuery(request.getCursor(), request.getLimit());
        CursorResult<Conversation> cursorResult = cursorConversationUseCase.execute(query);
        List<ConversationDTO> records = cursorResult.records().stream().map(conversation -> new ConversationDTO(
                conversation.id().value(),
                conversation.name().value()
        )).toList();
        return new CursorResult<>(records, cursorResult.nextCursor());
    }

}
