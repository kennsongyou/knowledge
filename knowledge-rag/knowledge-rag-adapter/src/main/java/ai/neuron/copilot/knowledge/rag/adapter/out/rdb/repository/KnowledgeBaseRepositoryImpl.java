package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaKnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.DifyDatasetId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
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
    public void create(KnowledgeBase knowledgeBase) {
        KnowledgeBasePO po = new KnowledgeBasePO();
        po.setKnowledgeBaseId(knowledgeBase.getId().value());
        po.setName(knowledgeBase.getName());
        po.setDescription(knowledgeBase.getDescription());
        po.setDifyDatasetId(knowledgeBase.getDifyDatasetId().value());
        jpaKnowledgeBaseRepository.save(po);
    }

    @Override
    public PageResult<KnowledgeBase> pageByKeyword(TenantId tenantId, String keyword, PageQuery pageQuery) {
        Pageable pageable = PageRequest.of(pageQuery.getPageNo() - 1, pageQuery.getPageSize());

        Page<KnowledgeBasePO> poPage;
        if (StringUtils.isBlank(keyword)) {
            poPage = jpaKnowledgeBaseRepository.findByTenantIdOrderByCreatedAtDesc(pageable, tenantId.value());
        } else {
            poPage = jpaKnowledgeBaseRepository
                    .findByTenantIdAndNameContainingIgnoreCaseOrderByCreatedAtDesc(pageable, tenantId.value(), keyword);
        }
        List<KnowledgeBase> domainPage = poPage.getContent().stream()
                .map(e -> KnowledgeBase.reconstitute(
                        KnowledgeBaseId.reconstitute(e.getKnowledgeBaseId()),
                        e.getName(),
                        e.getDescription(),
                        DifyDatasetId.reconstitute(e.getDifyDatasetId()))
                ).toList();
        return new PageResult<>(domainPage, poPage.getTotalElements(), pageQuery.getPageNo(), pageQuery.getPageSize());
    }

    @Override
    public void delete(KnowledgeBaseId knowledgeBaseId) {
        KnowledgeBasePO knowledgeBasePO = jpaKnowledgeBaseRepository.findByKnowledgeBaseId(knowledgeBaseId.value())
                .orElseThrow(RuntimeException::new);
        knowledgeBasePO.setDeletedAt(Instant.now());
        jpaKnowledgeBaseRepository.save(knowledgeBasePO);
    }

}
