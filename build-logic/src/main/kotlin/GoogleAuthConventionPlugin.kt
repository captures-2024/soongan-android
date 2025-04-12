import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import org.gradle.kotlin.dsl.dependencies

class GoogleAuthConventionPlugin : BaseConventionPlugin({
    dependencies {
        implementation(libs.android.credentials)
        implementation(libs.android.credentials.play.services.auth)

        implementation(libs.google.services)
        implementation(libs.google.id)
    }
})