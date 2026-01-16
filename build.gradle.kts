plugins {
	`java-library`
	`maven-publish`
	id("org.springframework.boot") version "3.5.9" apply false
	id("io.spring.dependency-management") version "1.1.7"
}

group = "ai.neuron.copilot"
version = "0.0.1"
description = "knowledge"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

repositories {
	mavenCentral()
}

subprojects {
	apply(plugin = "java-library")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "maven-publish")

	group = rootProject.group
	version = rootProject.version

	configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
		imports {
			mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.9")
		}
	}

	repositories {
		mavenCentral()
	}

	java {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(21))
		}
		withSourcesJar()
	}

	configure<PublishingExtension> {
		publications {
			create("maven", MavenPublication::class.java) {
				from(components["java"])
				pom {
					name.set(project.name)
					description.set(project.description ?: "${project.name} module")
				}
			}
		}

		repositories {
			mavenLocal()
		}
	}
}
