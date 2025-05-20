plugins {
  kotlin("jvm") version "2.1.21"
  id("org.jetbrains.dokka") version "2.0.0"
  `maven-publish`
}

group = "org.veupathdb.lib"
version = "0.1.0"
description = "Tools for validating JSON requests and reporting validation failures using JSON path notation."

repositories {
  mavenCentral()
}

kotlin {
  jvmToolchain {
    languageVersion = JavaLanguageVersion.of(21)
    vendor = JvmVendorSpec.AMAZON
  }
}

java {
  withSourcesJar()
}

val makeJavadocs = tasks.register<Jar>("makeJavadocs") {
  dependsOn(tasks.dokkaJavadoc)
  archiveClassifier.set("javadoc")
  from(file("build/dokka/javadoc"))
}

tasks.withType<Jar> {
  enabled = true
}

publishing {
  repositories {
    maven {
      name = "GitHub"
      url = uri("https://maven.pkg.github.com/VEuPathDB/lib-jpath-request-validation")
      credentials {
        username = System.getenv("GITHUB_USERNAME")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }

  publications {

    create<MavenPublication>("gpr") {
      from(components["java"])

      artifact(makeJavadocs)

      pom {
        name.set(project.name)
        description.set(project.description)
        url.set("https://github.com/VEuPathDB/lib-jpath-request-validation")

        licenses {
          license {
            name.set("Apache-2.0")
          }
        }

        developers {
          developer {
            id.set("epharper")
            name.set("Elizabeth Paige Harper")
            email.set("elizabeth.harper@foxcapades.io")
            url.set("https://github.com/foxcapades")
          }
        }

        scm {
          connection.set("scm:git:git://github.com/VEuPathDB/lib-jpath-request-validation.git")
          developerConnection.set("scm:git:ssh://github.com/VEuPathDB/lib-jpath-request-validation.git")
          url.set("https://github.com/VEuPathDB/lib-jpath-request-validation")
        }
      }
    }
  }
}
