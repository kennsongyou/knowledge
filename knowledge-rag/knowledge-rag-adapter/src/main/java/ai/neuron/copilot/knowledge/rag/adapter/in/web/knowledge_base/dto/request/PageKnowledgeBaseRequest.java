package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class PageKnowledgeBaseRequest {

    @Size(max = 64)
    String keyword;

    int pageNo = 1;

    int pageSize = 10;

}
