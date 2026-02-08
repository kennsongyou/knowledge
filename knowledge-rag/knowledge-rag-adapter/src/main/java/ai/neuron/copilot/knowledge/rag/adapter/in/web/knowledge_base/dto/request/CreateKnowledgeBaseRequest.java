package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateKnowledgeBaseRequest {

    String name;

    String description;

}
