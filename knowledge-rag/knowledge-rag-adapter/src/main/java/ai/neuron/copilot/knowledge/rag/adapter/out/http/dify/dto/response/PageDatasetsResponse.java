package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageDatasetsResponse {

    Object[] data;

    Boolean hasMore;

    Integer limit;

    Integer total;

    Integer page;

}
