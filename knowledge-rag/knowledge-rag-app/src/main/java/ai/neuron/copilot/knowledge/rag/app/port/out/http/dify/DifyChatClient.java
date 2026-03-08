package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.DifyAppAuthDTO;
import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.DifyChatRequest;

public interface DifyChatClient {

    void chat(DifyChatRequest difyChatRequest, DifyAppAuthDTO difyAppAuthDTO, String serverId);

}
