plugins {
	`java-library`
	`maven-publish`
}

version = "0.0.1"
description = "knowledge-foundation-data"

dependencies {
	implementation(project(":knowledge-foundation:knowledge-foundation-core"))
	implementation(libs.spring.boot.starter.data.jpa)
	implementation(libs.mybatis.plus.spring.boot.starter)
	implementation(libs.mybatis.plus.jsqlparser)
	implementation(libs.apache.commons.lang3)
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
