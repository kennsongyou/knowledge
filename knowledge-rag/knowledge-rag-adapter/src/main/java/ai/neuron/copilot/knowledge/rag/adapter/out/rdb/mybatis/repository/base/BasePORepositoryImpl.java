package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.repository.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BasePORepositoryImpl<M extends BaseMapper<T>, T>
        extends ServiceImpl<M, T>
        implements BasePORepository<T> {
}