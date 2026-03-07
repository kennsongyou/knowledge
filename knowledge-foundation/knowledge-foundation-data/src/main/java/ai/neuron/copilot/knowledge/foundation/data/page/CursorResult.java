package ai.neuron.copilot.knowledge.foundation.data.page;

import java.util.List;

public record CursorResult<T>(
        List<T> records,
        String nextCursor
) {}