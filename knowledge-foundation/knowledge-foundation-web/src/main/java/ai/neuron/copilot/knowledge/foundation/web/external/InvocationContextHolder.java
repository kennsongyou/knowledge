package ai.neuron.copilot.knowledge.foundation.web.external;

public final class InvocationContextHolder {

    private static final ThreadLocal<InvocationContext> CONTEXT = new ThreadLocal<>();

    public static void set(InvocationContext context) {
        CONTEXT.set(context);
    }

    public static InvocationContext get() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
