import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	`java-library`
	`maven-publish`
	alias(libs.plugins.spring.boot)
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-foundation-web"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(project(":knowledge-foundation:knowledge-foundation-core"))
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.validation)
	implementation(libs.apache.commons.lang3)
	implementation(libs.apache.commons.io)
	testImplementation(libs.spring.boot.starter.test)
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Jar> {
	archiveBaseName.set(project.name)
	archiveVersion.set(project.version.toString())
}

tasks.named<BootJar>("bootJar") {
	enabled = false
}
