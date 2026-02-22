package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.impl;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper.DifyDocumentMapper;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DifyDocumentPO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.DifyDocumentPORepository;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base.BasePORepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class DifyDocumentPORepositoryImpl extends BasePORepositoryImpl<DifyDocumentMapper, DifyDocumentPO>
        implements DifyDocumentPORepository {
}
