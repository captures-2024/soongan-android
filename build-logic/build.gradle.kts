@file:Suppress("DSL_SCOPE_VIOLATION", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
    `kotlin-dsl`
    kotlin("jvm").version(libs.versions.kotlin.kotlin.get())
}

gradlePlugin {
    val conventionPluginClasses = listOf(
        "android.application" to "AndroidApplication",
        "android.application.compose" to "AndroidApplicationCompose",
        "android.library" to "AndroidLibrary",
        "android.library.compose" to "AndroidLibraryCompose",
        "android.feature" to "AndroidFeature",
        "android.hilt" to "AndroidHilt",
        "android.room" to "AndroidRoomDatabase",
        "google.auth" to "GoogleAuth",
        "google.firebase" to "GoogleFirebase",
        "jvm.kotlin" to "JvmKotlin",
        "okhttp" to "OkHttp",
        "retrofit" to "Retrofit",
        "test.junit5" to "TestJunit5",
        "test.kotest" to "TestKotest",
    )

    plugins {
        conventionPluginClasses.forEach { pluginClass -> pluginRegister(pluginClass) }
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.google.ksp.gradle.plugin)
    compileOnly(libs.kotlin.compose.compiler.extension)

    compileOnly(files((libs as Any).javaClass.superclass.protectionDomain.codeSource.location))
}

fun NamedDomainObjectContainer<PluginDeclaration>.pluginRegister(data: Pair<String, String>) {
    val (pluginName, className) = data
    register(pluginName) {
        id = "captures2024.soongan.$pluginName"
        implementationClass = "${className}ConventionPlugin"
    }
}
