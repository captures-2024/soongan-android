package com.captures2024.soongan.plugin

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.captures2024.soongan.plugin.extension.testImplementation
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.AbstractTestTask
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.KotlinClosure2
import org.gradle.kotlin.dsl.withType
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class KotestPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        useTestPlatformForTarget()
        dependencies {
            testImplementation(libs.findLibrary("test-kotest-runner").get())
            testImplementation(libs.findLibrary("test-kotest-assertion").get())
            testImplementation(libs.findLibrary("test-kotest-property").get())
        }
    }
    private fun Project.useTestPlatformForTarget() {
        fun AbstractTestTask.setTestConfiguration() {
            outputs.upToDateWhen { false }
            testLogging {
                events = setOf(
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.FAILED,
                )
            }
            afterSuite(
                KotlinClosure2<TestDescriptor, TestResult, Unit>(
                    { desc, result ->
                        if (desc.parent == null) {
                            val output = "Results: ${result.resultType} " +
                                    "(${result.testCount} tests, " +
                                    "${result.successfulTestCount} passed, " +
                                    "${result.failedTestCount} failed, " +
                                    "${result.skippedTestCount} skipped)"
                            println(output)
                        }
                    },
                ),
            )
        }
        val isAndroidProject = pluginManager.hasPlugin("com.android.application") || pluginManager.hasPlugin("com.android.library")
        if (isAndroidProject) {
            val androidExtensions = if (pluginManager.hasPlugin("com.android.application")) {
                extensions.getByType<BaseAppModuleExtension>()
            } else if (pluginManager.hasPlugin("com.android.library")) {
                extensions.getByType<LibraryExtension>()
            } else {
                throw GradleException("The provided project does not have the Android plugin applied. ($name)")
            }
            androidExtensions.testOptions {
                unitTests.all { test ->
                    test.useJUnitPlatform()
                    if (!test.name.contains("debug", ignoreCase = true)) {
                        test.enabled = false
                    }
                }
            }
            tasks.withType<Test>().configureEach {
                setTestConfiguration()
            }
        } else {
            tasks.withType<Test>().configureEach {
                useJUnitPlatform()
                setTestConfiguration()
            }
        }
    }
}
