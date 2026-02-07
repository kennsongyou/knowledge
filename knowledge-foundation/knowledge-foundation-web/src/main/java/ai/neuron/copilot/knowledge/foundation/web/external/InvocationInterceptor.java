package ai.neuron.copilot.knowledge.foundation.web.external;


import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;


public interface InvocationInterceptor {

    void before(HttpRequest request, InvocationContext context);

    void after(HttpRequest request, ClientHttpResponse response, InvocationContext context);

    void onError(HttpRequest request, Throwable ex, InvocationContext context);

}
