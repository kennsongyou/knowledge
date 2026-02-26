//package ai.neuron.copilot.knowledge.foundation.data.rdb.jpa;
//
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//
//import ai.neuron.copilot.knowledge.foundation.core.context.UserContext;
//import ai.neuron.copilot.knowledge.foundation.core.context.ContextHolder;
//import ai.neuron.copilot.knowledge.foundation.core.context.domain.model.UserId;
//import java.util.Optional;
//
//@AutoConfiguration
//@ConditionalOnClass(JpaRepository.class)
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
//public class JpaAutoConfiguration {
//
//	@Bean
//	@ConditionalOnMissingBean
//	public AuditorAware<Long> auditorAware() {
//		return () -> Optional.of(ContextHolder.user()).map(UserContext::id).map(UserId::value);
//	}
//
//}
