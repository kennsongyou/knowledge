package ai.neuron.copilot.knowledge.rag.adapter.out.rdb.service;

import ai.neuron.copilot.knowledge.common.util.IdUtils;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.po.KnowledgeBasePO;
import ai.neuron.copilot.knowledge.rag.adapter.out.rdb.jpa.repository.JpaKnowledgeBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeBaseService {

	private final JpaKnowledgeBaseRepository jpaKnowledgeBaseRepository;

	@Autowired
	public KnowledgeBaseService(JpaKnowledgeBaseRepository jpaKnowledgeBaseRepository) {
		this.jpaKnowledgeBaseRepository = jpaKnowledgeBaseRepository;
	}

	// 创建知识库
	@Transactional
	public KnowledgeBasePO createKnowledgeBase(KnowledgeBasePO knowledgeBaseEntity) {
		// 生成唯一的 knowledgeBaseId
		if (knowledgeBaseEntity.getKnowledgeBaseId() == null) {
			knowledgeBaseEntity.setKnowledgeBaseId(IdUtils.uuidStr());
		}
		return jpaKnowledgeBaseRepository.save(knowledgeBaseEntity);
	}

	// 根据 ID 查询知识库
	public Optional<KnowledgeBasePO> getKnowledgeBaseById(Long id) {
		return jpaKnowledgeBaseRepository.findById(id);
	}

	// 根据 knowledgeBaseId 查询知识库
	public Optional<KnowledgeBasePO> getKnowledgeBaseByKnowledgeBaseId(String knowledgeBaseId) {
		return jpaKnowledgeBaseRepository.findByKnowledgeBaseId(knowledgeBaseId);
	}

	// 查询所有知识库
	public List<KnowledgeBasePO> getAllKnowledgeBases() {
		return jpaKnowledgeBaseRepository.findAll();
	}

	// 更新知识库
	@Transactional
	public KnowledgeBasePO updateKnowledgeBase(KnowledgeBasePO knowledgeBaseEntity) {
		// 更新审计字段
		knowledgeBaseEntity.setUpdatedAt(Instant.now());
		// 这里可以从 Spring Security 或 ThreadLocal 中获取当前用户ID
		return jpaKnowledgeBaseRepository.save(knowledgeBaseEntity);
	}

}
