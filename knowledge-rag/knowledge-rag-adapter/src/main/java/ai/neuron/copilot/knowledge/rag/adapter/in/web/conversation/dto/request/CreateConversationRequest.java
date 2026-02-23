package ai.neuron.copilot.knowledge.rag.adapter.in.web.conversation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateConversationRequest {

    String knowledgeBaseId;

}
