import com.android.build.gradle.LibraryExtension
import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.configureAndroid
import com.captures2024.soongan.plugin.libs
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.ANDROID_LIBRARY)
        apply(Plugins.KOTLIN_ANDROID)
    }

    extensions.configure<LibraryExtension> {
        configureAndroid(this)

        defaultConfig.apply {
            targetSdk = libs.versions.targetSdk.get().toInt()
        }
    }
})
