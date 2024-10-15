import com.android.build.api.dsl.ApplicationExtension
import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.configureCompose
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposeConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.ANDROID_APPLICATION)
    }

    extensions.configure<ApplicationExtension> {
        configureCompose(this)
    }
})
