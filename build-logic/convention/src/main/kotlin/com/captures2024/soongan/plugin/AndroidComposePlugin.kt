package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import com.captures2024.soongan.plugin.extension.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        extensions.getByType<BaseExtension>().apply {
            buildFeatures.apply {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = libs.findVersion("android.compose.compiler").get().requiredVersion
            }
        }

        dependencies {
            implementation(platform(libs.findLibrary("android-compose.bom").get()))
            implementation(libs.findBundle("compose").get())
        }
    }
}
