import com.android.build.api.dsl.ApplicationExtension
import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.configureAndroid
import com.captures2024.soongan.plugin.configureCompose
import com.captures2024.soongan.plugin.libs
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.ANDROID_APPLICATION)
        apply(Plugins.KOTLIN_ANDROID)
        apply(Plugins.GOOGLE_SERVICE)
    }

    extensions.configure<ApplicationExtension> {
        configureAndroid(this)

        configureCompose(this)

        defaultConfig {
            applicationId = libs.versions.applicationId.get()

            targetSdk = libs.versions.targetSdk.get().toInt()
            versionCode = libs.versions.versionCode.get().toInt()
            versionName = libs.versions.appVersion.get()
        }
    }
})
