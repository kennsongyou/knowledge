package ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.mysql.service;

import ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.mysql.entity.KnowledgeBaseEntity;
import ai.neuron.copilot.knowledge.rag.adapter.outbound.rdb.mysql.repository.KnowledgeBaseRepository;
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
	public KnowledgeBaseEntity createKnowledgeBase(KnowledgeBaseEntity knowledgeBaseEntity) {
		// 生成唯一的 knowledgeBaseId
		if (knowledgeBaseEntity.getKnowledgeBaseId() == null) {
			knowledgeBaseEntity.setKnowledgeBaseId(UUID.randomUUID().toString());
		}
		return knowledgeBaseRepository.save(knowledgeBaseEntity);
	}

	// 根据 ID 查询知识库
	public Optional<KnowledgeBaseEntity> getKnowledgeBaseById(Long id) {
		return knowledgeBaseRepository.findById(id);
	}

	// 根据 knowledgeBaseId 查询知识库
	public Optional<KnowledgeBaseEntity> getKnowledgeBaseByKnowledgeBaseId(String knowledgeBaseId) {
		return knowledgeBaseRepository.findByKnowledgeBaseId(knowledgeBaseId);
	}

	// 查询所有知识库
	public List<KnowledgeBaseEntity> getAllKnowledgeBases() {
		return knowledgeBaseRepository.findAll();
	}

	// 更新知识库
	@Transactional
	public KnowledgeBaseEntity updateKnowledgeBase(KnowledgeBaseEntity knowledgeBaseEntity) {
		// 更新审计字段
		knowledgeBaseEntity.setUpdatedAt(LocalDateTime.now());
		// 这里可以从 Spring Security 或 ThreadLocal 中获取当前用户ID
		knowledgeBaseEntity.setUpdatedBy(1L);
		return knowledgeBaseRepository.save(knowledgeBaseEntity);
	}

}
