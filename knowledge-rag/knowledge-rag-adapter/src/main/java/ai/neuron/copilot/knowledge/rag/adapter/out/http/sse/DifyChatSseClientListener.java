package ai.neuron.copilot.knowledge.rag.adapter.out.http.sse;

import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseClientListener;
import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseEvent;
import ai.neuron.copilot.knowledge.rag.adapter.out.http.sse.dto.DifyChatSseClientContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DifyChatSseClientListener implements SseClientListener<DifyChatSseClientContext> {

    @Override
    public void onOpen(DifyChatSseClientContext context) {
        System.out.println("on open");
    }

    @Override
    public void onMessage(DifyChatSseClientContext context, SseEvent event) {
        System.out.println(event);
    }

    @Override
    public void onError(DifyChatSseClientContext context, Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onClosed(DifyChatSseClientContext context) {
        System.out.println("on close");
    }

}
