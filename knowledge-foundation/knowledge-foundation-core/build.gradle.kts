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
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Jar> {
	archiveBaseName.set(project.name)
	archiveVersion.set(project.version.toString())
}
