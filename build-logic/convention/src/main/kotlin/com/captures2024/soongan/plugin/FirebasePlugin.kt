package com.captures2024.soongan.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class FirebasePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {


        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            val bom = libs.findLibrary("firebase").get()
            add("implementation", platform(bom))
//            "implementation"(libs.findLibrary("firebase.analytics").get())
//            "implementation"(libs.findLibrary("firebase.crashlytics").get())
//            "implementation"(libs.findLibrary("firebase.messaging").get())
        }
    }

}