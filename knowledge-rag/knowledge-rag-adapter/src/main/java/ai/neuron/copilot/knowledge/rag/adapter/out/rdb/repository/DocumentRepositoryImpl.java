package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaDocumentRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    public final JpaDocumentRepository jpaDocumentRepository;

    @Override
    public void create(Document document) {

    }

    @Override
    public PageResult<Document> pageByKeyword(TenantId tenantId, String keyword, PageQuery pageQuery) {
        return null;
    }

    @Override
    public void delete(DocumentId documentId) {

    }

//    @Override
//    public void create(KnowledgeBase knowledgeBase) {
//        KnowledgeBasePO po = new KnowledgeBasePO();
//        po.setKnowledgeBaseId(knowledgeBase.getId().value());
//        po.setName(knowledgeBase.getName());
//        po.setDescription(knowledgeBase.getDescription());
//        po.setDifyDatasetId(knowledgeBase.getDifyDatasetId().value());
//        jpaKnowledgeBaseRepository.save(po);
//    }
//
//    @Override
//    public PageResult<KnowledgeBase> pageByKeyword(TenantId tenantId, String keyword, PageQuery pageQuery) {
//        Pageable pageable = PageRequest.of(pageQuery.getPageNo() - 1, pageQuery.getPageSize());
//
//        Page<KnowledgeBasePO> poPage;
//        if (StringUtils.isBlank(keyword)) {
//            poPage = jpaKnowledgeBaseRepository.findByTenantIdOrderByCreatedAtDesc(pageable, tenantId.value());
//        } else {
//            poPage = jpaKnowledgeBaseRepository
//                    .findByTenantIdAndNameContainingIgnoreCaseOrderByCreatedAtDesc(pageable, tenantId.value(), keyword);
//        }
//        List<KnowledgeBase> domainPage = poPage.getContent().stream()
//                .map(e -> KnowledgeBase.reconstitute(
//                        KnowledgeBaseId.reconstitute(e.getKnowledgeBaseId()),
//                        e.getName(),
//                        e.getDescription(),
//                        DifyDatasetId.reconstitute(e.getDifyDatasetId()))
//                ).toList();
//        return new PageResult<>(domainPage, poPage.getTotalElements(), pageQuery.getPageNo(), pageQuery.getPageSize());
//    }
//
//    @Override
//    public void delete(KnowledgeBaseId id) {
//        KnowledgeBasePO knowledgeBasePO = jpaKnowledgeBaseRepository.findByKnowledgeBaseId(id.value())
//                .orElseThrow(ResourceNotExistException::new);
//        knowledgeBasePO.setDeletedAt(Instant.now());
//        jpaKnowledgeBaseRepository.save(knowledgeBasePO);
//    }

}
