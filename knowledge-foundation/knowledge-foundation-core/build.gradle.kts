plugins {
	`java-library`
	`maven-publish`
	alias(libs.plugins.spring.boot)
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-foundation-core"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(libs.spring.boot.starter)
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
