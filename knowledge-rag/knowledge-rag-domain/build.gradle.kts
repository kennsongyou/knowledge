plugins {
	`java-library`
	`maven-publish`
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-rag-domain"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(project(":knowledge-foundation:knowledge-foundation-core"))
	implementation(libs.apache.commons.lang3)
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
}

tasks.withType<Jar> {
	archiveBaseName.set(project.name)
	archiveVersion.set(project.version.toString())
}
