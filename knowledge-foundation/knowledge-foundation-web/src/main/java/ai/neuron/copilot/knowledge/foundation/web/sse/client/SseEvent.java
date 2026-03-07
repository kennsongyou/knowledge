package ai.neuron.copilot.knowledge.foundation.web.sse.client;

public record SseEvent(String id, String type, String data, String retry) {
}