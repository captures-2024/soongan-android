package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

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

            (this as ExtensionAware).configure<KotlinJvmOptions> {
                jvmTarget = "17"
            }
        }

        dependencies {
            add("coreLibraryDesugaring", libs.findLibrary("android.desugar.libs").get())
            add("implementation", libs.findLibrary("kotlin.kotlin").get())
            add("implementation", libs.findLibrary("kotlin.reflect").get())
            add("implementation", libs.findLibrary("kotlin.coroutines").get())
            add("implementation", libs.findLibrary("kotlin.datetime").get())
            add("implementation", libs.findLibrary("kotlin.serialization.json").get())
        }
    }
}
