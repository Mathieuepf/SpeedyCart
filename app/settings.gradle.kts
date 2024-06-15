pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{
            url = uri("https://sdk.squareup.com/public/android")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "SpeedyCart"
include(":app")
 