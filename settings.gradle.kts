pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven {
            url = uri("https://nexus.gluonhq.com/nexus/content/repositories/releases/")
        }
    }
}

rootProject.name = "callerble"
include("common", "service", "mobile", "desktop") 