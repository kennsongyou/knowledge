package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.DeleteKnowledgeBaseCommand;

public interface DeleteKnowledgeBaseUseCase {

    void execute(DeleteKnowledgeBaseCommand command);

}
