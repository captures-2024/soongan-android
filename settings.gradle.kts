import java.net.URI

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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
        maven { url = URI("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:testClasses"))

rootProject.name = "soongan"
include(":app")

include(
    ":core:analytics",
    ":core:analytics-android",
    ":core:auth",
    ":core:common",
    ":core:designSystem:icon",
    ":core:designSystem:ui",
    ":core:data",
    ":core:datastore",
    ":core:domain",
    ":core:model",
    ":core:navigator",
    ":core:network",
    ":core:viewmodel",
)

include(
    ":presentation:feature:sign",
    ":presentation:feature:sign-in",
    ":presentation:feature:sign-up",

    ":presentation:viewmodel",
)

include(
    ":feature:awards",
    ":feature:feed",
    ":feature:home",
    ":feature:intro",
    ":feature:main",
    ":feature:privacyPolicy",
    ":feature:profile",
    ":feature:sign",
    ":feature:signIn",
    ":feature:signUp",
    ":feature:termsOfUse",
    ":feature:welcome",
)
