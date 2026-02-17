package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.repository;

import ai.neuron.copilot.knowledge.foundation.data.page.PageQuery;
import ai.neuron.copilot.knowledge.foundation.data.page.PageResult;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.converter.DocumentConverter;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.DocumentMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.DocumentPORepository;
import ai.neuron.copilot.knowledge.rag.app.port.out.persistence.DocumentRepository;
import ai.neuron.copilot.knowledge.rag.domain.document.model.Document;
import ai.neuron.copilot.knowledge.rag.domain.document.model.DocumentId;
import ai.neuron.copilot.knowledge.rag.domain.knowledge_base.model.KnowledgeBaseId;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private final DocumentPORepository documentPORepository;

    private final DocumentMapper documentMapper;

    @Override
    public boolean save(Document document) {
        return documentPORepository.save(DocumentConverter.toPO(document));
    }

    @Override
    public Optional<Document> fetch(DocumentId documentId) {
        return documentPORepository.lambdaQuery()
                .eq(DocumentPO::getDocumentId, documentId.value())
                .oneOpt().map(DocumentConverter::toDomain);
    }

    @Override
    public PageResult<Document> pageByKeyword(PageQuery pageQuery, String keyword) {
        Page<DocumentPO> poPage = documentPORepository.lambdaQuery()
                .like(StringUtils.isNotBlank(keyword), DocumentPO::getDisplayName, keyword)
                .orderByDesc(DocumentPO::getCreatedAt)
                .page(new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize()));
        List<Document> domainList = poPage.getRecords().stream().map(DocumentConverter::toDomain).toList();
        return new PageResult<>(domainList, poPage.getTotal(), pageQuery.getPageNo(), pageQuery.getPageSize());
    }

    @Override
    public boolean delete(DocumentId documentId) {
        return documentPORepository.lambdaUpdate()
                .eq(DocumentPO::getDocumentId, documentId.value())
                .remove();
    }

    @Override
    public PageResult<Document> pageByKnowledgeBaseIdAndKeyword(PageQuery pageQuery, KnowledgeBaseId knowledgeBaseId, String keyword) {
        Page<DocumentPO> poPage = documentMapper.pageByKnowledgeBaseIdKeyword(
                new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize()), knowledgeBaseId.value(),keyword);
        List<Document> records = poPage.getRecords().stream().map(DocumentConverter::toDomain).toList();
        return new PageResult<>(records, poPage.getTotal(), poPage.getCurrent(), poPage.getSize());
    }

}
