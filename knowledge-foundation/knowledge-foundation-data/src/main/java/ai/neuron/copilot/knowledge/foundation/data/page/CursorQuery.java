package ai.neuron.copilot.knowledge.foundation.data.page;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CursorQuery (
    String cursor,
    int limit
) {}