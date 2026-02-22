package ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base;

import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.request.CreateKnowledgeBaseRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.request.PageKnowledgeBaseRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.request.UpdateKnowledgeBaseRequest;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.shared.KnowledgeBaseDTO;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.*;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.CreateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.DeleteKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.command.UpdateKnowledgeBaseCommand;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query.FetchKnowledgeBaseQuery;
import ai.neuron.copilot.knowledge.rag.app.port.in.knowledge_base.dto.query.PageKnowledgeBaseQuery;
import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/knowledge-bases")
public class KnowledgeBaseController {

    private final CreateKnowledgeBaseUseCase createKnowledgeBaseUseCase;

    private final FetchKnowledgeBaseUseCase fetchKnowledgeBaseUseCase;

    private final PageKnowledgeBaseUseCase pageKnowledgeBaseUseCase;

    private final DeleteKnowledgeBaseUseCase deleteKnowledgeBaseUseCase;

    private final UpdateKnowledgeBaseUseCase updateKnowledgeBaseUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateKnowledgeBaseResponse create(@RequestBody @Valid CreateKnowledgeBaseRequest request) {
        CreateKnowledgeBaseCommand command = new CreateKnowledgeBaseCommand(
                KnowledgeBaseImpl.fromValue(request.getImpl()),
                KnowledgeBaseName.create(request.getName()),
                KnowledgeBaseDescription.create(request.getDescription()));
        KnowledgeBaseId knowledgeBaseId = createKnowledgeBaseUseCase.execute(command);
        return new CreateKnowledgeBaseResponse(knowledgeBaseId.value());
    }

    @GetMapping("/{knowledge_base_id}")
    @ResponseStatus(HttpStatus.OK)
    public KnowledgeBaseDTO get(@PathVariable("knowledge_base_id") String knowledgeBaseId) {
        FetchKnowledgeBaseQuery query = new FetchKnowledgeBaseQuery(KnowledgeBaseId.reconstitute(knowledgeBaseId));
        KnowledgeBase kb = fetchKnowledgeBaseUseCase.execute(query);
        return new KnowledgeBaseDTO(
                kb.getId().value(),
                kb.getName().value(),
                kb.getDescription().value(),
                kb.getImpl().getValue()
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResult<KnowledgeBaseDTO> page(@ModelAttribute PageKnowledgeBaseRequest request) {
        PageKnowledgeBaseQuery query = new PageKnowledgeBaseQuery(request.getKeyword(),
                new PageQuery(request.getPageNo(), request.getPageSize()));
        PageResult<KnowledgeBase> pageResult = pageKnowledgeBaseUseCase.execute(query);
        List<KnowledgeBaseDTO> records = pageResult.records().stream().map(kb -> new KnowledgeBaseDTO(
                kb.getId().value(),
                kb.getName().value(),
                kb.getDescription().value(),
                kb.getImpl().getValue()
        )).toList();
        return new PageResult<>(records, pageResult.total(), pageResult.pageNo(), pageResult.pageSize());
    }

    @PatchMapping("/{knowledge_base_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("knowledge_base_id") String knowledgeBaseId,
                       @RequestBody @Valid UpdateKnowledgeBaseRequest request) {
        KnowledgeBaseId knowledgeBaseIdVO = KnowledgeBaseId.reconstitute(knowledgeBaseId);
        UpdateKnowledgeBaseCommand command = new UpdateKnowledgeBaseCommand(
                knowledgeBaseIdVO,
                KnowledgeBaseName.create(request.getName()),
                KnowledgeBaseDescription.create(request.getDescription()));
        updateKnowledgeBaseUseCase.execute(command);
    }

    @DeleteMapping("/{knowledge_base_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("knowledge_base_id") String knowledgeBaseId) {
        deleteKnowledgeBaseUseCase.execute(new DeleteKnowledgeBaseCommand(KnowledgeBaseId.reconstitute(knowledgeBaseId)));
    }

}
