package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.CreateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.response.CreateKnowledgeBaseResponse;

public interface CreateKnowledgeBaseUseCase {

    CreateKnowledgeBaseResponse execute(CreateKnowledgeBaseCommand command);

}
