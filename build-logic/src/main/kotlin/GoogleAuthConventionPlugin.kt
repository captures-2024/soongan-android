import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import org.gradle.kotlin.dsl.dependencies

class GoogleAuthConventionPlugin : BaseConventionPlugin({

    dependencies {
        implementation(libs.google.services)
        implementation(libs.google.gms.auth)
    }
})