// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.google.dagger.hilt.plugin)
        classpath(libs.android.gradle.plugin)
        classpath(libs.ktlint)
        classpath(libs.android.oss.plugin)
    }
}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.secret) apply false
    alias(libs.plugins.google.crashlytics) apply false

    alias(libs.plugins.ktlint) apply false

    alias(libs.plugins.junit5) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

apply {
    from("gradle/dependencyGraph.gradle")
}

true // Needed to make the Suppress annotation work for the plugins block