rootProject.name = "knowledge"

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(
    ":knowledge-common",
    ":knowledge-foundation:knowledge-foundation-core",
    ":knowledge-foundation:knowledge-foundation-web",
    ":knowledge-foundation:knowledge-foundation-webflux",
    ":knowledge-foundation:knowledge-foundation-persistence",
    ":knowledge-rag",
    ":knowledge-rag:knowledge-rag-adapter",
    ":knowledge-rag:knowledge-rag-app",
    ":knowledge-rag:knowledge-rag-bootstrap",
    ":knowledge-rag:knowledge-rag-contract",
    ":knowledge-rag:knowledge-rag-domain",
    ":knowledge-rag:knowledge-rag-infra",
    ":knowledge-rag:knowledge-rag-client",
)
