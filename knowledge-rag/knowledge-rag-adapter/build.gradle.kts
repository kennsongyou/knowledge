plugins {
    `java-library`
    `maven-publish`
}

description = "knowledge-rag-adapter"

dependencies {
    implementation(project(":knowledge-common"))
    implementation(project(":knowledge-foundation:knowledge-foundation-blob"))
    implementation(project(":knowledge-foundation:knowledge-foundation-core"))
    implementation(project(":knowledge-foundation:knowledge-foundation-web"))
    implementation(project(":knowledge-foundation:knowledge-foundation-data"))
    implementation(project(":knowledge-rag:knowledge-rag-app"))
    implementation(project(":knowledge-rag:knowledge-rag-domain"))
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.mybatis.plus.spring.boot.starter)
    implementation(libs.mybatis.plus.jsqlparser)
    implementation(libs.apache.commons.lang3)
    implementation(libs.apache.http.client5)
    implementation(platform(libs.squareup.okhttp3.bom))
    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.okhttp3.sse)
    implementation(libs.mysql.connector.j)
    implementation(libs.qcloud.cos.api)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks.withType<Jar> {
    archiveBaseName.set(project.name)
    archiveVersion.set(project.version.toString())
}
