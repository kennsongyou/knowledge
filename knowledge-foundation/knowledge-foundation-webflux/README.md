# knowledge-foundation-webflux

提供 WebFlux 的基础配置框架。

## 功能特性

- **WebFlux 配置**: 提供 WebFlux 的基础配置
- **路径隔离**: 所有 WebFlux 端点都在 `/stream` 路径下
- **独立启动**: 可以独立启动测试

## 项目结构

```
knowledge-foundation-webflux/
├── src/
│   └── main/
│       ├── java/
│       │   └── ai/neuron/copilot/knowledge/foundation/webflux/
│       │       ├── WebFluxApplication.java
│       │       └── config/
│       │           └── WebFluxConfig.java
│       └── resources/
│           └── application.yml
```

## 核心配置

### WebFluxConfig

```java
@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    @Override
    public void configurePathMatching(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/stream", c -> true);
    }
}
```

**作用**：
- 配置所有 WebFlux 端点的路径前缀为 `/stream`
- 与 Web MVC 的端点区分开

## 使用方式

### 1. 在业务项目中引入依赖

```kotlin
dependencies {
    implementation(project(":knowledge-foundation:knowledge-foundation-webflux"))
}
```

### 2. 创建 SSE Controller

```java
@RestController
@RequestMapping("/events")
public class MySseController {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Event " + sequence);
    }
}
```

访问: `http://localhost:8080/stream/events`

### 3. 创建 WebSocket Handler

```java
@Configuration
public class MyWebSocketConfiguration {

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return session -> session.send(
                session.receive()
                        .map(WebSocketMessage::getPayloadAsText)
                        .map(payload -> "Echo: " + payload)
                        .map(session::textMessage)
        );
    }

    @Bean
    public HandlerMapping webSocketHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(1);
        mapping.setUrlMap(Map.of(
                "/ws/**", myWebSocketHandler()
        ));
        return mapping;
    }
}
```

连接: `ws://localhost:8080/stream/ws`

### 4. 创建 WebSocket Handler Adapter

```java
@Configuration
public class WebSocketAdapterConfiguration {

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
```

## 注意事项

- 本项目只提供 WebFlux 的基础配置框架
- 不包含业务实现代码
- 所有 WebFlux 端点都在 `/stream` 路径下
- 业务项目的 Web MVC 端点不受影响
- WebSocket 端点建议使用 `/ws/**` 路径模式