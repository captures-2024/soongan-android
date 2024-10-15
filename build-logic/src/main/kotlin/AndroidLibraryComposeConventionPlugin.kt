import com.android.build.api.dsl.LibraryExtension
import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.configureCompose
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.ANDROID_LIBRARY)
    }

    extensions.configure<LibraryExtension> {
        configureCompose(this)
    }
})
