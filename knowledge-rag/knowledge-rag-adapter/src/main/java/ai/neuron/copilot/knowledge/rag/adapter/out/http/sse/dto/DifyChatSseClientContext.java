package ai.neuron.copilot.knowledge.rag.adapter.out.http.sse.dto;

import ai.neuron.copilot.knowledge.foundation.core.json.JsonCodec;
import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseClientContext;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.dto.DifyChatCallbackContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DifyChatSseClientContext extends SseClientContext {

    final String serverId;

    final String conversationId;

    final String knowledgeBaseId;

    String difyConversationId;

    String difyMessageId;

    public DifyChatSseClientContext(JsonCodec jsonCodec, String serverId, String conversationId,
                                    String knowledgeBaseId) {
        super(jsonCodec);
        this.serverId = serverId;
        this.conversationId = conversationId;
        this.knowledgeBaseId = knowledgeBaseId;
    }

    public DifyChatCallbackContext toCallBackContext() {
        DifyChatCallbackContext callbackContext = new DifyChatCallbackContext();
        callbackContext.setServerId(serverId);
        callbackContext.setConversationId(conversationId);
        callbackContext.setKnowledgeBaseId(knowledgeBaseId);
        callbackContext.setDifyConversationId(difyConversationId);
        callbackContext.setDifyMessageId(difyMessageId);
        return callbackContext;
    }

}
