package ai.neuron.copilot.knowledge.foundation.web.sse.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * SSE消息规范
 * 
 * 所有通过SSE推送的消息都应该使用此格式
 * 便于前端统一解析
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SseServerMessage {
    
    /**
     * 消息类型（业务自定义，如 "rag.progress", "conversation.message" 等）
     */
    private String type;
    
    /**
     * 消息数据（可以是任意结构）
     */
    private Object data;
    
    /**
     * 消息时间戳
     */
    private Long timestamp;
    
    /**
     * 业务相关ID（可选，如queryId、conversationId等）
     */
    private String businessId;
    
    /**
     * 错误码（仅当出错时）
     */
    private String errorCode;
    
    /**
     * 错误信息（仅当出错时）
     */
    private String errorMessage;

    /**
     * 创建成功消息
     */
    public static SseServerMessage success(String type, Object data) {
        return success(type, data, null);
    }

    /**
     * 创建成功消息（带businessId）
     */
    public static SseServerMessage success(String type, Object data, String businessId) {
        return SseServerMessage.builder()
            .type(type)
            .data(data)
            .businessId(businessId)
            .timestamp(System.currentTimeMillis())
            .build();
    }

    /**
     * 创建错误消息
     */
    public static SseServerMessage error(String type, String errorCode, String errorMessage) {
        return error(type, errorCode, errorMessage, null);
    }

    /**
     * 创建错误消息（带businessId）
     */
    public static SseServerMessage error(String type, String errorCode, String errorMessage, String businessId) {
        return SseServerMessage.builder()
            .type(type)
            .businessId(businessId)
            .errorCode(errorCode)
            .errorMessage(errorMessage)
            .timestamp(System.currentTimeMillis())
            .build();
    }

    /**
     * 创建心跳消息
     */
    public static SseServerMessage heartbeat() {
        return SseServerMessage.builder()
            .type("system.heartbeat")
            .data("pong")
            .timestamp(System.currentTimeMillis())
            .build();
    }
}