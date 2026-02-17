package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.impl;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.KnowledgeBaseDocumentMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.KnowledgeBaseDocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.KnowledgeBaseDocumentPORepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base.BasePORepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class KnowledgeBaseDocumentPORepositoryImpl extends BasePORepositoryImpl<KnowledgeBaseDocumentMapper,
        KnowledgeBaseDocumentPO> implements KnowledgeBaseDocumentPORepository {
}
