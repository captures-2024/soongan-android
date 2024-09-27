package com.captures2024.soongan.plugin

import com.android.build.gradle.BaseExtension
import com.captures2024.soongan.plugin.extension.androidTestImplementation
import com.captures2024.soongan.plugin.extension.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidTestPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(plugins) {
            apply("de.mannodermaus.android-junit5")
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        extensions.getByType<BaseExtension>().apply {
            defaultConfig {
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"
            }

            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                }
            }

            packagingOptions {
                resources.excludes.add("META-INF/LICENSE*")
            }
        }

        dependencies {
            testImplementation(libs.findLibrary("test-junit").get())
            testImplementation(libs.findLibrary("test-truth").get())
            testImplementation(libs.findLibrary("test-robolectric").get())
            androidTestImplementation(libs.findBundle("androidx-android-test").get())
        }
    }
}
