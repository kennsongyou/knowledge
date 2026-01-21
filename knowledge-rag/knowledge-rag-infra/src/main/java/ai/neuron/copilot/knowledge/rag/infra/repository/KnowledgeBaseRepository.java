package ai.neuron.copilot.knowledge.rag.infra.repository;

import ai.neuron.copilot.knowledge.rag.infra.entity.KnowledgeBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Long> {

	// 根据 knowledgeBaseId 查询
	Optional<KnowledgeBase> findByKnowledgeBaseId(String knowledgeBaseId);

	// 根据 tenantCode 查询所有未删除的知识库
	List<KnowledgeBase> findByTenantCodeAndDeletedFalse(String tenantCode);

	// 根据 name 查询（支持模糊查询）
	List<KnowledgeBase> findByNameContainingAndDeletedFalse(String name);

	// 根据 difyDatasetId 查询
	Optional<KnowledgeBase> findByDifyDatasetId(String difyDatasetId);

	// 根据 difyAppId 查询
	Optional<KnowledgeBase> findByDifyAppId(String difyAppId);

	// 根据 tenantCode 和 name 查询
	List<KnowledgeBase> findByTenantCodeAndNameContainingAndDeletedFalse(String tenantCode, String name);
}
