package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document;

import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.command.DeleteKnowledgeBaseDocumentCommand;

public interface DeleteKnowledgeBaseDocumentUseCase {

    void execute(DeleteKnowledgeBaseDocumentCommand command);

}
