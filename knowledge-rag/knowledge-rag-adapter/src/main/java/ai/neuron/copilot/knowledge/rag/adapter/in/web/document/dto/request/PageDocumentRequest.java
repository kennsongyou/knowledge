package ai.neuron.copilot.knowledge.rag.adapter.in.web.document.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class PageDocumentRequest {

    String keyword;

    int pageNo = 1;

    int pageSize = 10;

}
