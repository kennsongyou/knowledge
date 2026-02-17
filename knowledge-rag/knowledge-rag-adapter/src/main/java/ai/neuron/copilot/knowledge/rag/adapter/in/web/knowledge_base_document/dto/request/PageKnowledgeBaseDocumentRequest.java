package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base_document.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class PageKnowledgeBaseDocumentRequest {

    String keyword;

    int pageNo = 1;

    int pageSize = 10;

}
