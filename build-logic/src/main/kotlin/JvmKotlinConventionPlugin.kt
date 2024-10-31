import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.detektPlugins
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.libs
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

internal class JvmKotlinConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.JAVA_LIBRARY)
        apply(Plugins.KOTLIN_JVM)
    }

    extensions.getByType<JavaPluginExtension>().apply {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    extensions.configure<KotlinProjectExtension> {
        jvmToolchain(17)
    }

    dependencies {
        detektPlugins(libs.detekt.formatting)

        implementation(libs.kotlin.kotlin)
        implementation(libs.kotlin.coroutines)
        implementation(libs.kotlin.datetime)
    }
})
