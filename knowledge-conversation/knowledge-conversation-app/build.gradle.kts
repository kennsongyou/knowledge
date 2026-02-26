plugins {
    `java-library`
    `maven-publish`
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-conversation-app"

dependencies {
    implementation(project(":knowledge-common"))
    implementation(project(":knowledge-foundation:knowledge-foundation-blob"))
    implementation(project(":knowledge-foundation:knowledge-foundation-core"))
    implementation(project(":knowledge-foundation:knowledge-foundation-data"))
    implementation(project(":knowledge-conversation:knowledge-conversation-domain"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.apache.commons.lang3)
    implementation(libs.spring.tx)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks.withType<Jar> {
    archiveBaseName.set(project.name)
    archiveVersion.set(project.version.toString())
}
