package ai.neuron.copilot.knowledge.foundation.data.page;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CursorResult<T>(
        List<T> records,
        String nextCursor
) {}