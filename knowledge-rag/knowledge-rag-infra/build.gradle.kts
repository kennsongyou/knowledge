plugins {
	`java-library`
	`maven-publish`
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge-rag-infra"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(project(":knowledge-foundation:knowledge-foundation-core"))
	implementation(project(":knowledge-foundation:knowledge-foundation-web"))
	implementation(project(":knowledge-foundation:knowledge-foundation-data"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.mysql:mysql-connector-j")
	implementation("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Jar> {
	archiveBaseName.set(project.name)
	archiveVersion.set(project.version.toString())
}
