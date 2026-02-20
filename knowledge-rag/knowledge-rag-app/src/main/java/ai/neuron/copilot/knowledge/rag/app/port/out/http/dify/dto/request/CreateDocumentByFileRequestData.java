package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateDocumentByFileRequestData {

    @Builder.Default
    private String indexingTechnique = "high_quality";

    @Builder.Default
    private ProcessRule processRule = ProcessRule.builder().build();

    @Builder.Default
    private SummaryIndexSetting summaryIndexSetting = SummaryIndexSetting.builder().build();

    @Getter
    @Builder
    public static class ProcessRule {

        @Builder.Default
        private String mode = "automatic";

        @Builder.Default
        private Object rules = null;
    }

    @Getter
    @Builder
    public static class PreProcessingRule {

        private String id;

        private Boolean enabled;
    }

    @Getter
    @Builder
    public static class Segmentation {

        @Builder.Default
        private String separator = "###";

        @Builder.Default
        private Integer maxTokens = 500;
    }

    @Getter
    @Builder
    public static class SummaryIndexSetting {

        @Builder.Default
        private Boolean enable = true;

        @Builder.Default
        private String modelName = "text-embedding-3-small";

        @Builder.Default
        private String modelProviderName = "langgenius/openai/openai";

        @Builder.Default
        private String summaryPrompt = "summarize the text";

    }

    public static CreateDocumentByFileRequestData defaultRequest() {
        return CreateDocumentByFileRequestData.builder().build();
    }

}
