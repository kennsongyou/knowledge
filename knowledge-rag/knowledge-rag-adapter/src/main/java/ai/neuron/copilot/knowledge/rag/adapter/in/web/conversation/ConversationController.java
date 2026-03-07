package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.foundation.web.sse.server.SseServerManager;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.request.AddMessageRequest;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.AddMessageUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command.AddMessageCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@AllArgsConstructor
@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private final SseServerManager registry;

    private final AddMessageUseCase addMessageUseCase;

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SseEmitter> create(@RequestBody @Valid AddMessageRequest request) {
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

}
