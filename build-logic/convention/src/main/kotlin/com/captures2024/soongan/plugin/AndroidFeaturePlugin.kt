package com.captures2024.soongan.plugin

import com.captures2024.soongan.plugin.extension.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("com.android.library")
        }

        apply<AndroidComposePlugin>()
        apply<AndroidTestPlugin>()
        configureAndroidCommonPlugin()

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            implementation(project(":core:analytics-android"))
            implementation(project(":core:common"))
            implementation(project(":core:designSystem"))
            implementation(project(":core:domain"))
            implementation(project(":core:model"))
            implementation(project(":core:navigator"))

            implementation(libs.findLibrary("android-xml-core").get())
            implementation(libs.findBundle("lifecycle").get())
        }
    }
}
