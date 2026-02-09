package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotFoundException;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.DocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaDocumentRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mapper.DocumentMapper;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
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
    public Document get(DocumentId documentId, TenantId tenantId) {
        DocumentPO po = jpaDocumentRepository.findByDocumentIdAndTenantId(
                documentId.value(), tenantId.value()).orElseThrow(ResourceNotFoundException::new);
        return DocumentMapper.toDomain(po);
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
    public void delete(DocumentId documentId, TenantId tenantId) {
        DocumentPO po = jpaDocumentRepository.findByDocumentIdAndTenantId(documentId.value(), tenantId.value())
                .orElseThrow(ResourceNotFoundException::new);
        po.setDeletedAt(Instant.now());
        jpaDocumentRepository.save(po);
    }

}
