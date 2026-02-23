package ai.neuron.copilot.knowledge.rag.domain.sys.model;

import java.util.Map;

public record SysTenantDifyRegister(Long tenantId, String datasetId, Map<String, String> datasetMetadata,
                                    String appId, String appApiKey) {
}
