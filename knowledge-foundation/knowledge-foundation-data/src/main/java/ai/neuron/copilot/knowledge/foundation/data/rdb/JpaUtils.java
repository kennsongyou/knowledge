package ai.neuron.copilot.knowledge.foundation.data.rdb;

import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import org.springframework.data.domain.Page;

public final class JpaUtils {

    public static <T> PageResult<T> from(Page<T> page) {
        return new PageResult<>(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber() + 1,
                page.getSize()
        );
    }

}
