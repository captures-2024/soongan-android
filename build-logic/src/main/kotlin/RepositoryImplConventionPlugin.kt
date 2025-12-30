import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import com.captures2024.soongan.plugin.project
import org.gradle.kotlin.dsl.dependencies

class RepositoryImplConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.CUSTOM_LIBRARY)
        apply(Plugins.CUSTOM_HILT)
    }

    dependencies {
        implementation(project(path = ":core:analytics"))
        implementation(project(path = ":core:common"))
        implementation(project(path = ":core:model"))
    }
})