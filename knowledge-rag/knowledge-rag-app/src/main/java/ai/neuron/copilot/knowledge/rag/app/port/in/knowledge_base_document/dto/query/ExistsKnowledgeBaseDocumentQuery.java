package ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base_document.dto.query;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public record ExistsKnowledgeBaseDocumentQuery(
        KnowledgeBaseId knowledgeBaseId,
        DocumentId documentId,
        TenantId tenantId) {
}
