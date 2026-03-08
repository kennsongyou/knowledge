package ai.neuron.copilot.knowledge.rag.adapter.out.http.sse;

import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseClientListener;
import ai.neuron.copilot.knowledge.foundation.web.sse.client.SseEvent;
import ai.neuron.copilot.knowledge.rag.adapter.out.http.sse.dto.DifyChatSseClientContext;
import ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dify.DifyChatCallbackHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DifyChatSseClientListener implements SseClientListener<DifyChatSseClientContext> {

    private final DifyChatCallbackHandler difyChatCallbackHandler;

    @Override
    public void onOpen(DifyChatSseClientContext context) {
        difyChatCallbackHandler.handleOnOpen(context.getServerId());
    }

    @Override
    public void onMessage(DifyChatSseClientContext context, SseEvent sseEvent) {
        difyChatCallbackHandler.handleOnMessage(context.getServerId(), sseEvent.event(), sseEvent.data());
    }

    @Override
    public void onError(DifyChatSseClientContext context, Throwable t) {
        difyChatCallbackHandler.handleOnError(context.getServerId(), t);
    }

    @Override
    public void onClosed(DifyChatSseClientContext context) {
        difyChatCallbackHandler.handleOnClose(context.getServerId());
    }

}
