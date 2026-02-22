package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper;

import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.po.DocumentPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DocumentMapper extends BaseMapper<DocumentPO> {

    List<DocumentPO> listByKnowledgeBaseId(@Param("knowledgeBaseId") String knowledgeBaseId);

    Page<DocumentPO> pageByKnowledgeBaseIdKeyword(Page<DocumentPO> page,
                                                  @Param("knowledgeBaseId") String knowledgeBaseId,
                                                  @Param("keyword") String keyword);

}
