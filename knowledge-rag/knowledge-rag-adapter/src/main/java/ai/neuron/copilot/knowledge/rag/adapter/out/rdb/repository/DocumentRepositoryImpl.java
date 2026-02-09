package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.DocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaDocumentRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mapper.DocumentMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mapper.KnowledgeBaseMapper;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    public final JpaDocumentRepository jpaDocumentRepository;

    @Override
    public void save(Document document) {
        jpaDocumentRepository.save(DocumentMapper.toPO(document));
    }

    @Override
    public PageResult<Document> pageByKeyword(String keyword, PageQuery pageQuery, TenantId tenantId) {
        Pageable pageable = PageRequest.of(pageQuery.getPageNo() - 1, pageQuery.getPageSize());

        Page<DocumentPO> poPage;
        if (StringUtils.isBlank(keyword)) {
            poPage = jpaDocumentRepository.findByTenantIdOrderByCreatedAtDesc(pageable, tenantId.value());
        } else {
            poPage = jpaDocumentRepository
                    .findByTenantIdAndDisplayNameContainingIgnoreCaseOrderByCreatedAtDesc(pageable, tenantId.value(), keyword);
        }
        List<Document> domainPage = poPage.getContent().stream().map(DocumentMapper::toDomain).toList();
        return new PageResult<>(domainPage, poPage.getTotalElements(), pageQuery.getPageNo(), pageQuery.getPageSize());
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
