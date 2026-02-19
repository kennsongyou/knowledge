package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDocumentByFileResponse {

    private Document document;

    private String batch;

    @Getter
    @Setter
    public static class Document {

        private String id;

        private Integer position;

        private String dataSourceType;

        private Object dataSourceInfo;

        private String datasetProcessRuleId;

        private String name;

        private String createdFrom;

        private String createdBy;

        private Long createdAt;

        private Integer tokens;

        private String indexingStatus;

        private String error;

        private Boolean enabled;

        private Long disabledAt;

        private String disabledBy;

        private Boolean archived;

        private String displayStatus;

        private Integer wordCount;

        private Integer hitCount;

        private String docForm;
    }
}
