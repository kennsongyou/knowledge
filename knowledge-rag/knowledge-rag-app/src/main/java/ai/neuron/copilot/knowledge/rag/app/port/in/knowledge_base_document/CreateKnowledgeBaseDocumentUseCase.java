package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.CreateKnowledgeBaseDocumentCommand;

public interface CreateKnowledgeBaseDocumentUseCase {

    void execute(CreateKnowledgeBaseDocumentCommand command);

}
