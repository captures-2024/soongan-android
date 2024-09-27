import com.captures2024.soongan.plugin.androidExtensions
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.isAndroidProject
import com.captures2024.soongan.plugin.libs
import com.captures2024.soongan.plugin.testImplementation
import org.gradle.api.Project
import org.gradle.api.tasks.testing.AbstractTestTask
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.KotlinClosure2
import org.gradle.kotlin.dsl.withType
import org.gradle.kotlin.dsl.dependencies

class TestKotestConventionPlugin : BaseConventionPlugin({
    useTestPlatformForTarget()
    dependencies {
        implementation(libs.kotlin.reflect)
        testImplementation(libs.test.kotest.runner)
        testImplementation(libs.test.kotest.assertion)
        testImplementation(libs.test.kotest.property)
    }
})

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

    when (isAndroidProject) {
        true -> {
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
        }

        false -> tasks.withType<Test>().configureEach {
            useJUnitPlatform()
            setTestConfiguration()
        }
    }
}
