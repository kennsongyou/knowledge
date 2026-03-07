package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class UpdateKnowledgeBaseRequest {

    @NotBlank
    @Size(max = 64)
    String name;

    @Size(max = 255)
    String description;

}
