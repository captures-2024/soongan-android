import com.android.build.gradle.BaseExtension
import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.androidTestImplementation
import com.captures2024.soongan.plugin.libs
import com.captures2024.soongan.plugin.testImplementation
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class TestJunit5ConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.JUNIT5)
    }

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
        testImplementation(libs.test.junit)
        androidTestImplementation(libs.bundles.androidx.android.test)
        testImplementation(libs.bundles.junit5)
        androidTestImplementation(libs.test.junit5)
        androidTestImplementation(libs.test.junit5.params)
    }
})
