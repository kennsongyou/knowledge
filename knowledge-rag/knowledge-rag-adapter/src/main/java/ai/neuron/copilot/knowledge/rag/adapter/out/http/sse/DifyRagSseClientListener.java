package ai.neuron.copilot.knowledge.rag.adapter.out.http.sse;

import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseClientListener;
import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseEvent;
import ai.neuron.copilot.knowledge.rag.adapter.out.http.sse.dto.DifyRagSseClientContext;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.DifyRagMessageHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DifyRagSseClientListener implements SseClientListener<DifyRagSseClientContext> {

    private final DifyRagMessageHandler difyRagMessageHandler;

    @Override
    public void onOpen(DifyRagSseClientContext context) {
        System.out.println("on open");
    }

    @Override
    public void onMessage(DifyRagSseClientContext context, SseEvent event) {
        System.out.println(event);
    }

    @Override
    public void onError(DifyRagSseClientContext context, Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onClosed(DifyRagSseClientContext context) {
        System.out.println("on close");
    }

}
