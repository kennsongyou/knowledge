package ai.neuron.copilot.knowledge.rag.app.port.in.conversation.dto.command;

import ai.neuron.copilot.knowledge.rag.domain.conversation.model.ConversationId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;

public record CreateMessageCommand(
        String sseServerId,
        ConversationId conversationId,
        KnowledgeBaseId knowledgeBaseId,
        String query
) {}
