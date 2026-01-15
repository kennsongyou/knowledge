rootProject.name = "knowledge"

include(
    ":knowledge-common",
    ":knowledge-foundation",
    ":knowledge-foundation:knowledge-foundation-core",
    ":knowledge-foundation:knowledge-foundation-web",
    ":knowledge-rag",
    ":knowledge-rag:knowledge-rag-adapter",
    ":knowledge-rag:knowledge-rag-app",
    ":knowledge-rag:knowledge-rag-contract",
    ":knowledge-rag:knowledge-rag-domain",
    ":knowledge-rag:knowledge-rag-infra",
)
