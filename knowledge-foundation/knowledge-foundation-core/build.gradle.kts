plugins {
	`java-library`
	`maven-publish`
}

description = "knowledge-foundation-core"

dependencies {
	implementation(project(":knowledge-common"))
	implementation(libs.spring.context)
	implementation(libs.spring.boot.starter.aop)
	implementation(libs.apache.commons.lang3)
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
}


tasks.withType<Jar> {
	archiveBaseName.set(project.name)
	archiveVersion.set(project.version.toString())
}
