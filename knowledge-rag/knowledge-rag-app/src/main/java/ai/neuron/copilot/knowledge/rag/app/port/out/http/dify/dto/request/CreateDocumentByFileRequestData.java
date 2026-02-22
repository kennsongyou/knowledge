package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateDocumentByFileRequestData {

    @Builder.Default
    String indexingTechnique = "high_quality";

    @Builder.Default
    ProcessRule processRule = ProcessRule.builder().build();

    @Builder.Default
    SummaryIndexSetting summaryIndexSetting = SummaryIndexSetting.builder().build();

    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ProcessRule {

        @Builder.Default
        String mode = "automatic";

        @Builder.Default
        Object rules = null;
    }

    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class PreProcessingRule {

        String id;

        Boolean enabled;
    }

    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Segmentation {

        @Builder.Default
        String separator = "###";

        @Builder.Default
        Integer maxTokens = 500;
    }

    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SummaryIndexSetting {

        @Builder.Default
        Boolean enable = true;

        @Builder.Default
        String modelName = "text-embedding-3-small";

        @Builder.Default
        String modelProviderName = "langgenius/openai/openai";

        @Builder.Default
        String summaryPrompt = "summarize the text";

    }

    public static CreateDocumentByFileRequestData defaultRequest() {
        return CreateDocumentByFileRequestData.builder().build();
    }

}
