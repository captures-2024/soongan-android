import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import com.captures2024.soongan.plugin.project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply("captures2024.soongan.android.library")
        apply("captures2024.soongan.android.library.compose")
        apply("captures2024.soongan.android.hilt")
    }

    dependencies {
        implementation(project(path = ":core:analytics-android"))
        implementation(project(path = ":core:common"))
        implementation(project(path = ":core:designSystem"))
        implementation(project(path = ":core:domain"))
        implementation(project(path = ":core:model"))
        implementation(project(path = ":core:navigator"))

        implementation(libs.bundles.lifecycle)
    }
})
