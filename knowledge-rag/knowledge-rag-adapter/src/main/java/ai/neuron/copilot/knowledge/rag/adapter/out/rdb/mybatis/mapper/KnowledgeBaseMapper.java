package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.KnowledgeBasePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KnowledgeBaseMapper extends BaseMapper<KnowledgeBasePO> {
}
