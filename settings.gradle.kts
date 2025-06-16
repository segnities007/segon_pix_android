pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Segon_pix_android"
include(":app")
include(":presentation:component")
include(":presentation:feature-auth")
include(":domain:model")
include(":domain:repository")
include(":domain:usecase")
include(":data:repoitory-impl")
include(":data:remote")
include(":data:local")
include(":di")
include(":presentation:model")
include(":presentation:feature-hub")
include(":data:dto")
