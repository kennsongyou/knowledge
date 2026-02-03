package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.request.PageKnowledgeBaseRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.shared.KnowledgeBaseDTO;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.CreateKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.DeleteKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.PageKnowledgeBaseUseCase;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.CreateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.DeleteKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query.PageKnowledgeBaseQuery;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/knowledge-bases")
public class KnowledgeBaseController {

    private final CreateKnowledgeBaseUseCase createKnowledgeBaseUseCase;

    private final PageKnowledgeBaseUseCase pageKnowledgeBaseUseCase;

    private final DeleteKnowledgeBaseUseCase deleteKnowledgeBaseUseCase;

    @PostMapping
    public CreateKnowledgeBaseResponse create(@RequestBody CreateKnowledgeBaseCommand command) {
        KnowledgeBaseId knowledgeBaseId = createKnowledgeBaseUseCase.execute(command);
        return new CreateKnowledgeBaseResponse(knowledgeBaseId.value());
    }

    @GetMapping
    public PageResult<KnowledgeBaseDTO> page(@ModelAttribute PageKnowledgeBaseRequest request) {
        PageKnowledgeBaseQuery query = new PageKnowledgeBaseQuery(new TenantId(ContextHolder.tenant().id()),
                request.getKeyword(), new PageQuery(request.getPageNo(), request.getPageSize()));
        PageResult<KnowledgeBase> pageResult = pageKnowledgeBaseUseCase.execute(query);
        List<KnowledgeBaseDTO> records = pageResult.records().stream().map(kb -> new KnowledgeBaseDTO(
                kb.getId().value(),
                kb.getName(),
                kb.getDescription()
        )).toList();
        return new PageResult<>(
                records,
                pageResult.total(),
                pageResult.pageNo(),
                pageResult.pageSize()
        );
    }

    @DeleteMapping("/{knowledgeBaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("knowledgeBaseId") String knowledgeBaseId) {
        deleteKnowledgeBaseUseCase.execute(new DeleteKnowledgeBaseCommand(knowledgeBaseId));
    }

}
