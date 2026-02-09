package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseDescription;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseName;

public record UpdateKnowledgeBaseCommand(
        KnowledgeBaseId id,
        KnowledgeBaseName name,
        KnowledgeBaseDescription description,
        TenantId tenantId) {
}
