package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation;

import ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.request.CreateConversationRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateKnowledgeBaseResponse create(@RequestBody @Valid CreateConversationRequest request) {
        return null;
    }

}
