package ai.neuron.copilot.knowledge.rag.infra.service;

import ai.neuron.copilot.knowledge.rag.infra.entity.KnowledgeBase;
import ai.neuron.copilot.knowledge.rag.infra.repository.KnowledgeBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KnowledgeBaseService {

	private final KnowledgeBaseRepository knowledgeBaseRepository;

	@Autowired
	public KnowledgeBaseService(KnowledgeBaseRepository knowledgeBaseRepository) {
		this.knowledgeBaseRepository = knowledgeBaseRepository;
	}

	// 创建知识库
	@Transactional
	public KnowledgeBase createKnowledgeBase(KnowledgeBase knowledgeBase) {
		// 生成唯一的 knowledgeBaseId
		if (knowledgeBase.getKnowledgeBaseId() == null) {
			knowledgeBase.setKnowledgeBaseId(UUID.randomUUID().toString());
		}
		// 设置默认值
		if (knowledgeBase.getDeleted() == null) {
			knowledgeBase.setDeleted(false);
		}
		// 设置审计字段
		LocalDateTime now = LocalDateTime.now();
		knowledgeBase.setCreatedAt(now);
		knowledgeBase.setUpdatedAt(now);
		// 这里可以从 Spring Security 或 ThreadLocal 中获取当前用户ID
		knowledgeBase.setCreatedBy(1L);
		knowledgeBase.setUpdatedBy(1L);
		return knowledgeBaseRepository.save(knowledgeBase);
	}

	// 根据 ID 查询知识库
	public Optional<KnowledgeBase> getKnowledgeBaseById(Long id) {
		return knowledgeBaseRepository.findById(id);
	}

	// 根据 knowledgeBaseId 查询知识库
	public Optional<KnowledgeBase> getKnowledgeBaseByKnowledgeBaseId(String knowledgeBaseId) {
		return knowledgeBaseRepository.findByKnowledgeBaseId(knowledgeBaseId);
	}

	// 根据 tenantCode 查询所有未删除的知识库
	public List<KnowledgeBase> getKnowledgeBasesByTenantCode(String tenantCode) {
		return knowledgeBaseRepository.findByTenantCodeAndDeletedFalse(tenantCode);
	}

	// 根据名称模糊查询知识库
	public List<KnowledgeBase> getKnowledgeBasesByName(String name) {
		return knowledgeBaseRepository.findByNameContainingAndDeletedFalse(name);
	}

	// 根据 tenantCode 和名称模糊查询知识库
	public List<KnowledgeBase> getKnowledgeBasesByTenantCodeAndName(String tenantCode, String name) {
		return knowledgeBaseRepository.findByTenantCodeAndNameContainingAndDeletedFalse(tenantCode, name);
	}

	// 查询所有知识库
	public List<KnowledgeBase> getAllKnowledgeBases() {
		return knowledgeBaseRepository.findAll();
	}

	// 更新知识库
	@Transactional
	public KnowledgeBase updateKnowledgeBase(KnowledgeBase knowledgeBase) {
		// 更新审计字段
		knowledgeBase.setUpdatedAt(LocalDateTime.now());
		// 这里可以从 Spring Security 或 ThreadLocal 中获取当前用户ID
		knowledgeBase.setUpdatedBy(1L);
		return knowledgeBaseRepository.save(knowledgeBase);
	}

	// 软删除知识库
	@Transactional
	public void deleteKnowledgeBase(Long id) {
		Optional<KnowledgeBase> knowledgeBaseOptional = knowledgeBaseRepository.findById(id);
		if (knowledgeBaseOptional.isPresent()) {
			KnowledgeBase knowledgeBase = knowledgeBaseOptional.get();
			knowledgeBase.setDeleted(true);
			knowledgeBase.setUpdatedAt(LocalDateTime.now());
			// 这里可以从 Spring Security 或 ThreadLocal 中获取当前用户ID
			knowledgeBase.setUpdatedBy(1L);
			knowledgeBaseRepository.save(knowledgeBase);
		}
	}

	// 批量软删除知识库
	@Transactional
	public void deleteKnowledgeBases(List<Long> ids) {
		List<KnowledgeBase> knowledgeBases = knowledgeBaseRepository.findAllById(ids);
		LocalDateTime now = LocalDateTime.now();
		knowledgeBases.forEach(knowledgeBase -> {
			knowledgeBase.setDeleted(true);
			knowledgeBase.setUpdatedAt(now);
			// 这里可以从 Spring Security 或 ThreadLocal 中获取当前用户ID
			knowledgeBase.setUpdatedBy(1L);
		});
		knowledgeBaseRepository.saveAll(knowledgeBases);
	}
}
