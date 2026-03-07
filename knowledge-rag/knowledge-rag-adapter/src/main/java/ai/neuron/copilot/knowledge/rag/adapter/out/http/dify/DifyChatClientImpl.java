package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.DifyChatClient;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DifyChatClientImpl implements DifyChatClient {

    @Override
    public void chat(ChatRequest chatRequest) {

    }
//    private final OkHttpClient okHttpClient;
//    private final String baseUrl;
//    private final String apiKey;
//    private final EventSourceListener listener;
//
//    @Override
//    public void chat(String conversationId, String query) {
//
//        String url = baseUrl + "/v1/chat-messages";
//
//        String body = """
//                {
//                  "inputs": {},
//                  "query": "%s",
//                  "response_mode": "streaming",
//                  "conversation_id": "%s",
//                  "user": "default-user"
//                }
//                """.formatted(query, conversationId);
//
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("Authorization", "Bearer " + apiKey)
//                .addHeader("Content-Type", "application/json")
//                .post(okhttp3.RequestBody.create(
//                        body,
//                        okhttp3.MediaType.parse("application/json")
//                ))
//                .build();
//
//        EventSource.Factory factory = EventSources.createFactory(okHttpClient);
//
//        factory.newEventSource(request, listener);
}
