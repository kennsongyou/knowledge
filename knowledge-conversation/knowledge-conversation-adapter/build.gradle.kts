plugins {
    `java-library`
    `maven-publish`
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-conversation-adapter"


dependencies {
    implementation(project(":knowledge-common"))
    implementation(project(":knowledge-foundation:knowledge-foundation-core"))
    implementation(project(":knowledge-foundation:knowledge-foundation-web"))
    implementation(project(":knowledge-foundation:knowledge-foundation-data"))
    implementation(project(":knowledge-conversation:knowledge-conversation-app"))
    implementation(project(":knowledge-conversation:knowledge-conversation-domain"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.mybatis.plus.spring.boot.starter)
    implementation(libs.apache.commons.lang3)
    implementation(libs.apache.http.client5)
    implementation(libs.mysql.connector.j)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}


tasks.withType<Jar> {
    archiveBaseName.set(project.name)
    archiveVersion.set(project.version.toString())
}
