import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.project
import org.gradle.kotlin.dsl.dependencies

class DataSourceConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.CUSTOM_LIBRARY)
        apply(Plugins.CUSTOM_HILT)
        apply(Plugins.CUSTOM_OKHTTP)
        apply(Plugins.CUSTOM_RETROFIT)
    }

    dependencies {
        implementation(project(path = ":core:model"))
    }
})