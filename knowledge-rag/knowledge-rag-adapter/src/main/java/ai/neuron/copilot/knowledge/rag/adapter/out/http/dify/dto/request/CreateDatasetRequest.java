package ai.neuron.copilot.knowledge.rag.adapter.out.http.dify.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateDatasetRequest {

    String name;

    String description;

    String indexingTechnique = "high_quality";

    String permission = "all_team_members";

    String provider = "vendor";

    String embeddingModel = "text-embedding-3-large";

    String embeddingModelProvider = "langgenius/openai/openai";

}
