package com.captures2024.soongan.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class JUnit5Plugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(plugins) {
            apply("de.mannodermaus.android-junit5")
        }
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        dependencies {
            add("testImplementation", libs.findBundle("junit5").get())
            add("androidTestImplementation", libs.findLibrary("junit5").get())
            add("androidTestImplementation", libs.findLibrary("junit5.params").get())
            add("androidTestImplementation", libs.findLibrary("junit5.android.test.core").get())
            add("androidTestRuntimeOnly", libs.findLibrary("junit5.android.test.runner").get())
        }
    }
}
