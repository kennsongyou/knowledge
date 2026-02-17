package ai.neuron.copilot.knowledge.foundation.data.page;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class PageQuery {

    long pageNo;

    long pageSize;

}