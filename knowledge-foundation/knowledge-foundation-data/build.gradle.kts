plugins {
	`java-library`
	`maven-publish`
}

version = "0.0.1"
description = "knowledge-foundation-data"

dependencies {
	api(project(":knowledge-foundation:knowledge-foundation-core"))
	
	compileOnly(libs.spring.boot.starter.data.jpa)
	compileOnly(libs.mysql.connector.j)
	compileOnly(libs.redisson.spring.boot.starter)
	
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
