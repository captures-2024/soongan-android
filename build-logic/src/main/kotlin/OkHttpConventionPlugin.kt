import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import org.gradle.kotlin.dsl.dependencies

class OkHttpConventionPlugin : BaseConventionPlugin({
    dependencies {
        implementation(platform(libs.okhttp.bom))
        implementation(libs.okhttp)
        implementation(libs.okhttp.logging.interceptor)
    }
})
