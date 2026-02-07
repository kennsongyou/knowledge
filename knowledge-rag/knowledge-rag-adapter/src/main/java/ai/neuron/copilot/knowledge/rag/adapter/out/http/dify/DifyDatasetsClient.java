package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify;

import ai.neuron.copilot.knowledge.rag.adapter.out.http.dify.dto.response.PageDatasetsResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface DifyDatasetsClient {

    @GetExchange("/datasets")
    PageDatasetsResponse pageDatasets(@RequestParam(name = "keyword", required = false) String keyword,
                                      @RequestParam(name = "tag_ids", required = false) List<String> tagIds,
                                      @RequestParam(name = "page", defaultValue = "1") Integer page,
                                      @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                                      @RequestParam(name = "include_all", defaultValue = "false") Boolean includeAll
    );


}