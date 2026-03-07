package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base_document.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class PageKnowledgeBaseDocumentRequest {

    String keyword;

    int pageNo = 1;

    int pageSize = 10;

}
