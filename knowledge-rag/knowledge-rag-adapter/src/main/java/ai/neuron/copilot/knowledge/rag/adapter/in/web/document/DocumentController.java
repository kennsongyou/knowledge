//package ai.neuron.copilot.knowledge.rag.adapter.in.web.document;
//
//import ai.neuron.copilot.knowledge.rag.adapter.in.web.knowledge_base.dto.response.CreateKnowledgeBaseResponse;
//import ai.neuron.copilot.knowledge.rag.app.port.in.document.CreateDocumentUseCase;
//import ai.neuron.copilot.knowledge.rag.app.port.in.document.dto.command.CreateDocumentCommand;
//import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/documents")
//public class DocumentController {
//
//    private final CreateDocumentUseCase createDocumentUseCase;
//
//    @PostMapping
//    public CreateKnowledgeBaseResponse create(@RequestBody CreateDocumentCommand command) {
//        KnowledgeBaseId knowledgeBaseId = createDocumentUseCase.execute(command);
//        return new CreateKnowledgeBaseResponse(knowledgeBaseId.value());
//    }
//
//}
