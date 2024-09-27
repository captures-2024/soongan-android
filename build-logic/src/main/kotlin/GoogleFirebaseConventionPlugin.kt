import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import org.gradle.kotlin.dsl.dependencies

class GoogleFirebaseConventionPlugin : BaseConventionPlugin({

    dependencies {
        implementation(platform(libs.google.firebase.bom))
        implementation(libs.bundles.firebase)
    }
})
