pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven {
            url = uri("https://nexus.gluonhq.com/nexus/content/repositories/releases/")
        }
        maven {
            url = uri("https://nexus.gluonhq.com/nexus/content/groups/public/")
        }
    }
}

rootProject.name = "callerble"
include("common", "service", "mobile", "desktop") 