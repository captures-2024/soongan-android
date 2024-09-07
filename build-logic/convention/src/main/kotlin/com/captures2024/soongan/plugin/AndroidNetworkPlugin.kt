package com.captures2024.soongan.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidNetworkPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            add("implementation", libs.findBundle("retrofit").get())
            add("implementation", platform(libs.findLibrary("okhttp.bom").get()))
            add("implementation", libs.findLibrary("okhttp.logging.interceptor").get())
        }
    }

}