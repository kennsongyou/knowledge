package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command;

import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseDescription;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseName;

public record CreateKnowledgeBaseCommand(
        KnowledgeBaseImpl impl,
        KnowledgeBaseName name,
        KnowledgeBaseDescription description) {
}
