package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.CreateDatasetRequest;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.UpdateDocumentMetadataRequest;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.response.CreateDocumentByFileResponse;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.response.PageDatasetsResponse;
import org.springframework.core.io.Resource;

import java.util.List;

public interface DifyDatasetsClient {

    PageDatasetsResponse pageDatasets(String keyword, List<String> tagIds, Integer page, Integer limit,
                                      Boolean includeAll);

    PageDatasetsResponse createDataset(CreateDatasetRequest request);

    CreateDocumentByFileResponse createDocumentByFile(String datasetId, String data, Resource file);

    default void deleteDataset(String datasetId) {}

    void updateDocumentMetadata(String datasetId, UpdateDocumentMetadataRequest request);

}