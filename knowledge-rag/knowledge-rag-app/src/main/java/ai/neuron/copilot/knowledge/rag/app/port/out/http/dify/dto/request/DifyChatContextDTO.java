package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DifyChatContextDTO {

    String serverId;

    String conversationId;

    String knowledgeBaseId;

}
