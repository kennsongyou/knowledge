plugins {
	`java-library`
	`maven-publish`
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-common"

dependencies {
	implementation(libs.commons.lang3)
	implementation(libs.mapstruct.core)
	implementation("com.fasterxml.uuid:java-uuid-generator:5.2.0")
	annotationProcessor(libs.mapstruct.processor)
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
	testImplementation(libs.bundles.testing)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Jar> {
	archiveBaseName.set(project.name)
	archiveVersion.set(project.version.toString())
}
