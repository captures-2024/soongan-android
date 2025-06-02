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
    ":core:data",
    ":core:domain",
    ":core:model",
    ":core:navigator",
)

include(
    ":data:datastore",
    ":data:network",
)

include(
    ":data:source:auth",
    ":data:source:contest",
    ":data:source:fcm",
    ":data:source:home",
    ":data:source:member",
    ":data:source:notification",
    ":data:source:report",
    ":data:source:system",
)

include(
    ":domain:usecase",
)

include(
    ":presentation:designSystem:icon",
    ":presentation:designSystem:ui",

    ":presentation:feature:main",
    ":presentation:feature:main-awards",
    ":presentation:feature:main-feed",
    ":presentation:feature:main-home",
    ":presentation:feature:main-post",
    ":presentation:feature:main-profile",

    ":presentation:feature:sign",
    ":presentation:feature:sign-in",
    ":presentation:feature:sign-up",

    ":presentation:viewmodel",
)
