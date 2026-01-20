plugins {
	`java-library`
	`maven-publish`
}

description = "knowledge-rag-client"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(libs.spring.boot.starter.web)
	runtimeOnly(libs.http.client5)
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