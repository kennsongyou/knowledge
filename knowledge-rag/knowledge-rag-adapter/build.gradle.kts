plugins {
	`java-library`
	`maven-publish`
}

description = "knowledge-rag-adapter"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(project(":knowledge-foundation:knowledge-foundation-core"))
	implementation(project(":knowledge-foundation:knowledge-foundation-web"))
	implementation(project(":knowledge-rag:knowledge-rag-contract"))
	implementation(libs.spring.boot.starter.web)
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
}

tasks.withType<Jar> {
	archiveBaseName.set(project.name)
	archiveVersion.set(project.version.toString())
}
