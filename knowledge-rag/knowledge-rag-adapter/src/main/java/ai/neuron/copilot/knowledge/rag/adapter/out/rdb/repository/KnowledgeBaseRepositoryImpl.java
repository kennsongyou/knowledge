package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.TenantId;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.UserId;
import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaKnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mapper.KnowledgeBaseMapper;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class KnowledgeBaseRepositoryImpl implements KnowledgeBaseRepository {

    public final JpaKnowledgeBaseRepository jpaKnowledgeBaseRepository;

    @Override
    public Optional<KnowledgeBase> fetch(KnowledgeBaseId knowledgeBaseId) {
        return jpaKnowledgeBaseRepository.findByKnowledgeBaseId(
                knowledgeBaseId.value()).map(KnowledgeBaseMapper::toDomain);
    }

    @Override
    public void save(KnowledgeBase knowledgeBase) {
        jpaKnowledgeBaseRepository.save(KnowledgeBaseMapper.toPO(knowledgeBase));
    }

    @Override
    public Optional<KnowledgeBase> getByName(KnowledgeBaseName name) {
        return jpaKnowledgeBaseRepository.findByName(name.value()).map(KnowledgeBaseMapper::toDomain);
    }

    @Override
    public PageResult<KnowledgeBase> pageByKeyword(String keyword, PageQuery pageQuery) {
        Pageable pageable = PageRequest.of(pageQuery.getPageNo() - 1, pageQuery.getPageSize());

        Page<KnowledgeBasePO> poPage;
        if (StringUtils.isBlank(keyword)) {
            poPage = jpaKnowledgeBaseRepository.findByOrderByCreatedAtDesc(pageable);
        } else {
            poPage = jpaKnowledgeBaseRepository.findByNameContainingIgnoreCaseOrderByCreatedAtDesc(pageable, keyword);
        }
        List<KnowledgeBase> domainPage = poPage.getContent().stream().map(KnowledgeBaseMapper::toDomain).toList();
        return new PageResult<>(domainPage, poPage.getTotalElements(), pageQuery.getPageNo(), pageQuery.getPageSize());
    }

    @Override
    public boolean delete(KnowledgeBaseId knowledgeBaseId) {
        return jpaKnowledgeBaseRepository.softDelete(knowledgeBaseId.value());
    }

}
