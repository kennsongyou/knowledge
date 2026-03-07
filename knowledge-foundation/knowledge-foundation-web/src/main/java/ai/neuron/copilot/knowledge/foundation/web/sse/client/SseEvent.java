package ai.neuron.copilot.knowledge.foundation.web.sse.client;

public record SseEvent(String id, String event, String data, String retry) {
}