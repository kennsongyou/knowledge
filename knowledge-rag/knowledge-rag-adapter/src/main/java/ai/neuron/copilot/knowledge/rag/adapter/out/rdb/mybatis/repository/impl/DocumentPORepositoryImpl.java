package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.impl;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.DocumentMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.DocumentPORepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base.BasePORepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentPORepositoryImpl extends BasePORepositoryImpl<DocumentMapper, DocumentPO>
        implements DocumentPORepository {
}
