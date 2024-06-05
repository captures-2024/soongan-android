pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "soongan"
include(":app")

include(
    ":core:analytics",
    ":core:auth",
    ":core:common",
    ":core:designSystem",
    ":core:data",
    ":core:datastore",
    ":core:domain",
    ":core:model",
    ":core:network"
)

include(
    ":feature:awards",
    ":feature:feed",
    ":feature:home",
    ":feature:intro",
    ":feature:main",
    ":feature:navigator",
    ":feature:privacyPolicy",
    ":feature:profile",
    ":feature:sign",
    ":feature:signIn",
    ":feature:signUp",
    ":feature:termsOfUse",
    ":feature:welcome",
)