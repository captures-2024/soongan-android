package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import com.captures2024.soongan.plugin.extension.implementation
import com.captures2024.soongan.plugin.extension.androidTestImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with (plugins) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        extensions.getByType<BaseExtension>().apply {
            buildFeatures.apply {
                compose = true
            }
        }

        tasks.withType<KotlinJvmCompile>().configureEach {
            compilerOptions {
                freeCompilerArgs.add("-P")
                freeCompilerArgs.add("plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$rootDir/report/compose-metrics")
                freeCompilerArgs.add("-P")
                freeCompilerArgs.add("plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$rootDir/report/compose-reports")

            }
        }

        dependencies {
            implementation(platform(libs.findLibrary("android-compose-bom").get()))
            implementation(libs.findBundle("compose").get())
            androidTestImplementation(platform(libs.findLibrary("android-compose-bom").get()))
        }
    }
}
