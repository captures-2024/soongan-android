package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import com.captures2024.soongan.plugin.extension.implementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

class KotlinAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("kotlin-android")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        extensions.getByType<BaseExtension>().apply {
            setCompileSdkVersion(libs.findVersion("compileSdk").get().requiredVersion.toInt())

            defaultConfig {
                minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
                targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
            }

            compileOptions {
                isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            extensions.configure<KotlinProjectExtension> {
                jvmToolchain(17)
            }
        }

        dependencies {
            add("coreLibraryDesugaring", libs.findLibrary("android-desugar-libs").get())
            add("detektPlugins", libs.findLibrary("detekt-formatting").get())
            implementation(libs.findLibrary("kotlin-kotlin").get())
            implementation(libs.findLibrary("kotlin-reflect").get())
            implementation(libs.findLibrary("kotlin-coroutines").get())
            implementation(libs.findLibrary("kotlin-datetime").get())
            implementation(libs.findLibrary("kotlin-serialization-json").get())
        }
    }
}
