package ai.neuron.copilot.knowledge.foundation.web.sse.client;

public class SseEventParser {
    public static SseEvent parse(String sseChunk) {
        if (sseChunk == null || sseChunk.isEmpty()) return null;
        String[] lines = sseChunk.split("\n");
        String id = null, event = null, data = null, retry = null;
        StringBuilder dataBuilder = new StringBuilder();
        for (String line : lines) {
            if (line.startsWith("id:")) id = line.substring(3).trim();
            else if (line.startsWith("event:")) event = line.substring(6).trim();
            else if (line.startsWith("data:")) dataBuilder.append(line.substring(5)).append('\n');
            else if (line.startsWith("retry:")) retry = line.substring(6).trim();
        }
        if (!dataBuilder.isEmpty()) data = dataBuilder.substring(0, dataBuilder.length() - 1);
        return new SseEvent(id, event == null ? "message" : event, data, retry);
    }
}