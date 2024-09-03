package com.captures2024.soongan.plugin

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
            add("implementation", project(":core:analytics"))
            add("implementation", project(":core:common"))
            add("implementation", project(":core:designSystem"))
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:model"))
            add("implementation", project(":core:navigator"))

            add("implementation", libs.findLibrary("core.ktx").get())
            add("implementation", libs.findBundle("lifecycle").get())
            add("implementation", libs.findLibrary("timber").get())
        }
    }
}