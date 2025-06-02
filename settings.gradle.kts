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
    ":data:repository:auth",
    ":data:repository:auth-impl",
    ":data:repository:contest",
    ":data:repository:contest-impl",
    ":data:repository:fcm",
    ":data:repository:fcm-impl",
    ":data:repository:home",
    ":data:repository:home-impl",
    ":data:repository:member",
    ":data:repository:member-impl",
    ":data:repository:notification",
    ":data:repository:notification-impl",
    ":data:repository:report",
    ":data:repository:report-impl",
    ":data:repository:system",
    ":data:repository:system-impl",
    ":data:repository:token",
    ":data:repository:token-impl",
)

include(
    ":data:source:auth",
    ":data:source:auth-impl",
    ":data:source:contest",
    ":data:source:contest-impl",
    ":data:source:fcm",
    ":data:source:fcm-impl",
    ":data:source:home",
    ":data:source:home-impl",
    ":data:source:member",
    ":data:source:member-impl",
    ":data:source:notification",
    ":data:source:notification-impl",
    ":data:source:report",
    ":data:source:report-impl",
    ":data:source:system",
    ":data:source:system-impl",
    ":data:source:token",
    ":data:source:token-impl",
    ":data:source:utils",
)

include(
    ":domain:usecase:auth",
    ":domain:usecase:auth-impl",
    ":domain:usecase:contest",
    ":domain:usecase:contest-impl",
    ":domain:usecase:fcm",
    ":domain:usecase:fcm-impl",
    ":domain:usecase:home",
    ":domain:usecase:home-impl",
    ":domain:usecase:member",
    ":domain:usecase:member-impl",
    ":domain:usecase:notification",
    ":domain:usecase:notification-impl",
    ":domain:usecase:report",
    ":domain:usecase:report-impl",
    ":domain:usecase:system",
    ":domain:usecase:system-impl",
    ":domain:usecase:token",
    ":domain:usecase:token-impl",
    ":domain:usecase:utils",
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
