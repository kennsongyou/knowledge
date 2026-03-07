package ai.neuron.copilot.knowledge.foundation.data.page;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PageResult<T>(
        List<T> records,
        long total,
        long pageNo,
        long pageSize
) {}