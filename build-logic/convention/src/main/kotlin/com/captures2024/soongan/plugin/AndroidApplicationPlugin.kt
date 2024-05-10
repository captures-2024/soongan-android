package com.captures2024.soongan.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(plugins) {
            apply("com.android.application")
            apply("com.google.gms.google-services")
        }
        configureAndroidCommonPlugin()
    }
}
