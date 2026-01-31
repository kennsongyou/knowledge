import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	`java-library`
	`maven-publish`
	alias(libs.plugins.spring.boot)
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-foundation-webflux"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(project(":knowledge-foundation:knowledge-foundation-core"))
	implementation(libs.spring.boot.starter.webflux)
	testImplementation(libs.spring.boot.starter.test)
	implementation(libs.apache.commons.lang3)
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
	archiveFileName.set("${project.name}-${project.version}.jar")
}