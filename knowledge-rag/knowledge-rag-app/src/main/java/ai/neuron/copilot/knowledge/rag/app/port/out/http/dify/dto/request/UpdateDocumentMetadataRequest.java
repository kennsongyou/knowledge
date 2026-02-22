package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateDocumentMetadataRequest {

    List<OperationData> operationData;

    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OperationData {

        String documentId;

        List<MetadataItem> metadataList;
    }

    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class MetadataItem {

        String id;

        String value;

        String name;

        @Builder.Default
        String type = "string";
    }

}
