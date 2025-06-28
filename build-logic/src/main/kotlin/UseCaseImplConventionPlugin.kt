import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import com.captures2024.soongan.plugin.project
import org.gradle.kotlin.dsl.dependencies

class UseCaseImplConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.CUSTOM_JVM)
    }

    dependencies {
        implementation(project(path = ":core:analytics"))
        implementation(project(path = ":core:model"))

        implementation(libs.javax.inject)
    }
})