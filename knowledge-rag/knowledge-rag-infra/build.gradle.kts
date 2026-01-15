import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.spring.boot)
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-rag-infra"

dependencies {
    implementation(project(":knowledge-common"))
    implementation(project(":knowledge-foundation:knowledge-foundation-core"))
    implementation(project(":knowledge-foundation:knowledge-foundation-web"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(libs.bundles.testing)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    archiveBaseName.set(project.name)
    archiveVersion.set(project.version.toString())
}

tasks.named<BootJar>("bootJar") {
    archiveFileName.set("${project.name}-${project.version}.jar")
}
