package ai.neuron.copilot.knowledge.foundation.data.page;

public record CursorQuery (
    String cursor,
    int limit
) {}