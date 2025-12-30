import com.android.build.gradle.LibraryExtension
import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.configureAndroid
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

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

    dependencies {
        implementation(libs.kotlin.kotlin)
        implementation(libs.kotlin.coroutines)
        implementation(libs.kotlin.datetime)
    }
})
