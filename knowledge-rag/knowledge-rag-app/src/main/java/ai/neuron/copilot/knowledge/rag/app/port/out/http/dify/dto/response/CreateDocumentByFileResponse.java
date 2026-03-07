package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.response;

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
public class CreateDocumentByFileResponse {

    Document document;

    String batch;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Document {

        String id;

        Integer position;

        String dataSourceType;

        Object dataSourceInfo;

        String datasetProcessRuleId;

        String name;

        String createdFrom;

        String createdBy;

        Long createdAt;

        Integer tokens;

        String indexingStatus;

        String error;

        Boolean enabled;

        Long disabledAt;

        String disabledBy;

        Boolean archived;

        String displayStatus;

        Integer wordCount;

        Integer hitCount;

        String docForm;
    }

}
