import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	`java-library`
	`maven-publish`
	alias(libs.plugins.spring.boot)
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-rag-bootstrap"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(project(":knowledge-foundation:knowledge-foundation-core"))
	implementation(project(":knowledge-foundation:knowledge-foundation-web"))
	implementation(project(":knowledge-foundation:knowledge-foundation-data"))
	implementation(project(":knowledge-rag:knowledge-rag-adapter"))
	implementation(project(":knowledge-rag:knowledge-rag-app"))
	implementation(project(":knowledge-rag:knowledge-rag-contract"))
	implementation(project(":knowledge-rag:knowledge-rag-domain"))
	implementation(libs.spring.boot.starter)
	testImplementation(libs.spring.boot.starter.test)
	testImplementation("com.h2database:h2")
	testImplementation("com.mysql:mysql-connector-j")
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
