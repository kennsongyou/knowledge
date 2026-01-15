plugins {
	`java-library`
	`maven-publish`
	alias(libs.plugins.spring.boot) apply false
	alias(libs.plugins.dependency.management)
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
