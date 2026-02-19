package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.DifyDatasetsClient;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.CreateDatasetRequest;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.response.CreateDocumentByFileResponse;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.response.PageDatasetsResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange
public interface DifyDatasetsClientImpl extends DifyDatasetsClient {

    @GetExchange("/datasets")
    PageDatasetsResponse pageDatasets(@RequestParam(name = "keyword", required = false) String keyword,
                                      @RequestParam(name = "tag_ids", required = false) List<String> tagIds,
                                      @RequestParam(name = "page", defaultValue = "1") Integer page,
                                      @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                                      @RequestParam(name = "include_all", defaultValue = "false") Boolean includeAll
    );

    @PostExchange("/datasets")
    PageDatasetsResponse createDataset(@RequestBody CreateDatasetRequest request);


    @PostExchange(value = "/datasets/{dataset_id}/document/create-by-file",
            contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
    CreateDocumentByFileResponse createDocumentByFile(@PathVariable("dataset_id") String datasetId,
                                                      @RequestPart("data") String data,
                                                      @RequestPart("file") Resource file);

}