package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("ai.neuron.copilot.knowledge.rag.adapter.out.rdb.mybatis.mapper")
public class MybatisConfig {
}
