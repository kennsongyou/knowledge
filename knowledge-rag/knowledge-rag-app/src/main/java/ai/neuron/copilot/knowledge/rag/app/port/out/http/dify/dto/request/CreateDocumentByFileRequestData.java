package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDocumentByFileRequestData {

    private String indexingTechnique;

    private ProcessRule processRule;

    private SummaryIndexSetting summaryIndexSetting;

    @Getter
    @Setter
    public static class ProcessRule {

        private String mode;

        private Rules rules;
    }

    @Getter
    @Setter
    public static class Rules {

        private Segmentation segmentation;

    }

    @Getter
    @Setter
    public static class Segmentation {

        private String separator;

        private Integer maxTokens;
    }

    @Getter
    @Setter
    public static class SummaryIndexSetting {

        private Boolean enable;

        private String modelName;

        private String modelProviderName;

        private String summaryPrompt;

    }
}
