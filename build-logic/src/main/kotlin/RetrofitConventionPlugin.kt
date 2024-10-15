import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import org.gradle.kotlin.dsl.dependencies

class RetrofitConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.KOTLIN_SERIALIZATION)
    }

    dependencies {
        implementation(libs.retrofit)
        implementation(libs.retrofit.kotlin.serialization.converter)
        implementation(libs.kotlin.serialization.json)
    }
})
