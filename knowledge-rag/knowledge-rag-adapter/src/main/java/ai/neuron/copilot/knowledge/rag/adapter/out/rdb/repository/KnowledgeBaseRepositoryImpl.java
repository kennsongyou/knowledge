package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter.KnowledgeBaseConverter;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.KnowledgeBasePORepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.KnowledgeBaseRepository;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class KnowledgeBaseRepositoryImpl implements KnowledgeBaseRepository {

    private final KnowledgeBasePORepository knowledgeBasePORepository;

    @Override
    public Optional<KnowledgeBase> fetch(KnowledgeBaseId knowledgeBaseId) {
        return knowledgeBasePORepository.lambdaQuery()
                .eq(KnowledgeBasePO::getKnowledgeBaseId, knowledgeBaseId.value())
                .oneOpt().map(KnowledgeBaseConverter::toDomain);
    }

    @Override
    public boolean save(KnowledgeBase knowledgeBase) {
        return knowledgeBasePORepository.save(KnowledgeBaseConverter.toPO(knowledgeBase));
    }

    @Override
    public boolean update(KnowledgeBase knowledgeBase) {
        return knowledgeBasePORepository.lambdaUpdate()
                .eq(KnowledgeBasePO::getKnowledgeBaseId, knowledgeBase.getId().value())
                .set(KnowledgeBasePO::getName, knowledgeBase.getName().value())
                .set(KnowledgeBasePO::getDescription, knowledgeBase.getDescription().value())
                .update();
    }

    @Override
    public Optional<KnowledgeBase> getByName(KnowledgeBaseName name) {
        return knowledgeBasePORepository.lambdaQuery()
                .like(KnowledgeBasePO::getName, name.value())
                .oneOpt().map(KnowledgeBaseConverter::toDomain);
    }

    @Override
    public PageResult<KnowledgeBase> pageByKeyword(String keyword, PageQuery pageQuery) {
        Page<KnowledgeBasePO> page = knowledgeBasePORepository.lambdaQuery()
                .like(StringUtils.isNotBlank(keyword), KnowledgeBasePO::getName, keyword)
                .orderByDesc(KnowledgeBasePO::getCreatedAt)
                .page(new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize()));
        List<KnowledgeBase> domainList = page.getRecords().stream().map(KnowledgeBaseConverter::toDomain).toList();
        return new PageResult<>(domainList, page.getTotal(), pageQuery.getPageNo(), pageQuery.getPageSize());
    }

    @Override
    public boolean delete(KnowledgeBaseId knowledgeBaseId) {
        return knowledgeBasePORepository.lambdaUpdate()
                .eq(KnowledgeBasePO::getKnowledgeBaseId, knowledgeBaseId.value())
                .remove();
    }

}
