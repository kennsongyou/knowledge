package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify;

import ai.neuron.copilot.knowledge.foundation.core.json.SnakeCaseJsonCodec;
import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseClientManager;
import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseRequest;
import ai.neuron.copilot.knowledge.rag.adapter.out.http.sse.DifyChatSseClientListener;
import ai.neuron.copilot.knowledge.rag.adapter.out.http.sse.dto.DifyChatSseClientContext;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.DifyChatCallbackHandler;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.DifyChatClient;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.DifyChatContextDTO;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.DifyChatRequest;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.DifyChatStartBundleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DifyChatClientImpl implements DifyChatClient {

    private final SnakeCaseJsonCodec snakeCaseJsonCodec;

    private final SseClientManager sseClientManager;

    private final DifyProperties difyProperties;

    private final DifyChatCallbackHandler difyChatCallbackHandler;

    @Override
    public void chat(DifyChatStartBundleDTO difyChatStartBundleDTO) {
        DifyChatRequest difyChatRequest = difyChatStartBundleDTO.getDifyChatRequest();
        DifyChatContextDTO difyChatContextDTO = difyChatStartBundleDTO.getDifyChatContextDTO();
        DifyChatSseClientListener clientListener = new DifyChatSseClientListener(difyChatCallbackHandler);
        DifyChatSseClientContext clientContext = new DifyChatSseClientContext(
                snakeCaseJsonCodec,
                difyChatContextDTO.getServerId(),
                difyChatContextDTO.getConversationId(),
                difyChatContextDTO.getKnowledgeBaseId()
        );
        Map<String, Object> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + difyChatStartBundleDTO.getDifyAppMetadataDTO().getAppApiKey());
        SseRequest sseRequest = SseRequest.builder()
                .url(difyProperties.getDomain() + "/chat-messages")
                .method("POST")
                .headers(headers)
                .jsonBody(snakeCaseJsonCodec.encode(difyChatRequest))
                .build();
        sseClientManager.register(sseRequest, clientContext, clientListener);
    }

}
