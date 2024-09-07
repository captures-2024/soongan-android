package com.captures2024.soongan.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class KotlinSerializationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            add("implementation", libs.findLibrary("kotlin.serialization.json").get())
        }
    }
}
