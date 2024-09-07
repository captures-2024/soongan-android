package com.captures2024.soongan.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("com.android.library")
        }
        configureAndroidCommonPlugin()
    }
}
