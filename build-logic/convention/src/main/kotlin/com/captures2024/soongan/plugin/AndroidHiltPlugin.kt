package com.captures2024.soongan.plugin

import com.captures2024.soongan.plugin.extension.implementation
import com.captures2024.soongan.plugin.extension.ksp
import com.captures2024.soongan.plugin.extension.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("com.google.devtools.ksp")
            apply("com.google.dagger.hilt.android")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            implementation(libs.findLibrary("google-dagger-hilt").get())
            ksp(libs.findLibrary("google-dagger-hilt-compiler").get())
            testImplementation(libs.findLibrary("google-dagger-hilt-testing").get())
            testImplementation(libs.findLibrary("google-dagger-hilt-testing-compiler").get())
            ksp(libs.findLibrary("google-dagger-hilt-testing-compiler").get())
            implementation(libs.findLibrary("google-dagger-hilt-navigation-compose").get())
        }
    }
}
