package ai.neuron.copilot.knowledge.rag.app.service.conversation.dify;

import ai.neuron.copilot.knowledge.foundation.core.exception.SystemException;
import ai.neuron.copilot.knowledge.foundation.core.json.SnakeCaseJsonCodec;
import ai.neuron.copilot.knowledge.rag.app.port.out.context.CurrentOperatorProvider;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.DifyChatClient;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.DifyAppAuthDTO;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.DifyChatRequest;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.SysTenantDifyRegisterRepository;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.ConversationImplementer;
import ai.neuron.copilot.knowledge.rag.app.service.conversation.dto.ChatStartDTO;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.Conversation;
import ai.neuron.copilot.knowledge.rag.domain.conversation.model.DifyConversationMetadata;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseImpl;
import ai.neuron.copilot.knowledge.rag.domain.sys.model.SysTenantDifyRegister;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DifyConversationImpl implements ConversationImplementer {

    private final SnakeCaseJsonCodec snakeCaseJsonCodec;

    private final CurrentOperatorProvider currentOperatorProvider;

    private final SysTenantDifyRegisterRepository sysTenantDifyRegisterRepository;

    private final DifyChatClient difyChatClient;

    @Override
    public void start(ChatStartDTO chatStartDTO) {
        DifyConversationMetadata metadata;
        String difyConversationId = null;
        if (!chatStartDTO.firstMessage()) {
            metadata = snakeCaseJsonCodec.decode(chatStartDTO.conversation().getMetadata(), DifyConversationMetadata.class);
            difyConversationId = Optional.ofNullable(metadata).map(DifyConversationMetadata::difyConversationId)
                    .orElseThrow(() -> {
                        log.error("metadata is null, cannot get difyConversationId");
                        return new SystemException();
                    });
        }
        SysTenantDifyRegister sysTenantDifyRegister = sysTenantDifyRegisterRepository
                .fetchByTenantId(currentOperatorProvider.tenantId()).orElseThrow(SystemException::new);

        DifyChatRequest difyChatRequest = DifyChatRequest.builder()
                .query(chatStartDTO.query())
                .inputs(Map.of("neuron_knowledge_base_id", chatStartDTO.knowledgeBase().getId().value()))
                .user(String.valueOf(currentOperatorProvider.userId().value()))
                .conversationId(difyConversationId)
                .traceId(currentOperatorProvider.traceId())
                .build();
        DifyAppAuthDTO difyAppAuthDTO = DifyAppAuthDTO.builder()
                .appId(sysTenantDifyRegister.appId())
                .appApiKey(sysTenantDifyRegister.appApiKey())
                .build();
        difyChatClient.chat(difyChatRequest, difyAppAuthDTO, chatStartDTO.serverId());
    }

    @Override
    public KnowledgeBaseImpl impl() {
        return KnowledgeBaseImpl.DIFY;
    }

}
