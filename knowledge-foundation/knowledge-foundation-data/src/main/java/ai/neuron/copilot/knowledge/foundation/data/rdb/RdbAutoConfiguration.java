package ai.neuron.copilot.knowledge.foundation.data.rdb;

import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
import ai.neuron.copilot.knowledge.foundation.data.rdb.mybatis.KnowledgeMetaObjectHandler;
import ai.neuron.copilot.knowledge.foundation.data.rdb.mybatis.KnowledgeTenantHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.UserId;

import java.util.Optional;

@AutoConfiguration
public class RdbAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = {
            "org.springframework.data.jpa.repository.JpaRepository",
            "org.springframework.data.jpa.repository.config.EnableJpaAuditing"
    })
    @org.springframework.data.jpa.repository.config.EnableJpaAuditing(auditorAwareRef = "auditorAware")
    static class JpaConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public org.springframework.data.domain.AuditorAware<Long> auditorAware() {
            return () -> Optional.of(ContextHolder.user())
                    .map(UserContext::id)
                    .map(UserId::value);
        }

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = "com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor")
    static class MybatisConfiguration {

        @Bean
        @ConditionalOnMissingBean(name = "mybatisPlusInterceptor")
        public com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor mybatisPlusInterceptor() {
            com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor interceptor =
                    new com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor(KnowledgeTenantHandler.getInstance()));
            interceptor.addInnerInterceptor(new com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor(com.baomidou.mybatisplus.annotation.DbType.MYSQL));
            interceptor.addInnerInterceptor(new com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor());
            return interceptor;
        }

        @Bean
        @ConditionalOnMissingBean(name = "metaObjectHandler")
        public com.baomidou.mybatisplus.core.handlers.MetaObjectHandler metaObjectHandler() {
            return new KnowledgeMetaObjectHandler();
        }

    }

}
