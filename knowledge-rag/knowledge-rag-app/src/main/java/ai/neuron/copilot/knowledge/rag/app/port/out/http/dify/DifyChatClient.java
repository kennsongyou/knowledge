package ai.neuron.copilot.knowledge.rag.app.port.out.http.dify;

import ai.neuron.copilot.knowledge.rag.app.port.out.http.dify.dto.request.DifyChatStartBundleDTO;

public interface DifyChatClient {

    void chat(DifyChatStartBundleDTO difyChatStartBundleDTO);

}
