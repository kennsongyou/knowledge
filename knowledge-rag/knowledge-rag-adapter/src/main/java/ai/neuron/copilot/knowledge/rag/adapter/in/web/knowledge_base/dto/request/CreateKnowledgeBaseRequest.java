package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateKnowledgeBaseRequest {

    @NotBlank
    @Size(max = 255)
    String impl;

    @NotBlank
    @Size(max = 64)
    String name;

    @Size(max = 255)
    String description;

}
