import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.ksp
import com.captures2024.soongan.plugin.kspTest
import com.captures2024.soongan.plugin.libs
import com.captures2024.soongan.plugin.testImplementation
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.HILT)
        apply(Plugins.KSP)
    }

    dependencies {
        implementation(libs.google.dagger.hilt)
        ksp(libs.google.dagger.hilt.compiler)
        testImplementation(libs.google.dagger.hilt.testing)
        kspTest(libs.google.dagger.hilt.testing.compiler)
        implementation(libs.google.dagger.hilt.navigation.compose)
    }
})
