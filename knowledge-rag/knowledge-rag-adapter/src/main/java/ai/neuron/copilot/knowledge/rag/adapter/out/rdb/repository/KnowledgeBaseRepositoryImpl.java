package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaKnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mapper.KnowledgeBaseMapper;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;
import ai.neuron.copilot.knowledge.foundation.core.exception.ResourceNotExistException;
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
public class KnowledgeBaseRepositoryImpl implements KnowledgeBaseRepository {

    public final JpaKnowledgeBaseRepository jpaKnowledgeBaseRepository;

    @Override
    public KnowledgeBase get(KnowledgeBaseId knowledgeBaseId, TenantId tenantId) {
        KnowledgeBasePO po = jpaKnowledgeBaseRepository.findByKnowledgeBaseIdAndTenantId(
                knowledgeBaseId.value(), tenantId.value()).orElseThrow(ResourceNotExistException::new);
        return KnowledgeBaseMapper.toDomain(po);
    }

    @Override
    public void save(KnowledgeBase knowledgeBase) {
        jpaKnowledgeBaseRepository.save(KnowledgeBaseMapper.toPO(knowledgeBase));
    }

    @Override
    public PageResult<KnowledgeBase> pageByKeyword(String keyword, PageQuery pageQuery, TenantId tenantId) {
        Pageable pageable = PageRequest.of(pageQuery.getPageNo() - 1, pageQuery.getPageSize());

        Page<KnowledgeBasePO> poPage;
        if (StringUtils.isBlank(keyword)) {
            poPage = jpaKnowledgeBaseRepository.findByTenantIdOrderByCreatedAtDesc(pageable, tenantId.value());
        } else {
            poPage = jpaKnowledgeBaseRepository
                    .findByTenantIdAndNameContainingIgnoreCaseOrderByCreatedAtDesc(pageable, tenantId.value(), keyword);
        }
        List<KnowledgeBase> domainPage = poPage.getContent().stream().map(KnowledgeBaseMapper::toDomain).toList();
        return new PageResult<>(domainPage, poPage.getTotalElements(), pageQuery.getPageNo(), pageQuery.getPageSize());
    }

    @Override
    public void delete(KnowledgeBaseId knowledgeBaseId, TenantId tenantId) {
        KnowledgeBasePO knowledgeBasePO = jpaKnowledgeBaseRepository.findByKnowledgeBaseIdAndTenantId(
                knowledgeBaseId.value(), tenantId.value()).orElseThrow(ResourceNotExistException::new);
        knowledgeBasePO.setDeletedAt(Instant.now());
        jpaKnowledgeBaseRepository.save(knowledgeBasePO);
    }

}
