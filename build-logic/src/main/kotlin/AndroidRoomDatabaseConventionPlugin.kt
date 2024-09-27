
import com.captures2024.soongan.plugin.Plugins
import com.captures2024.soongan.plugin.implementation
import com.captures2024.soongan.plugin.ksp
import com.captures2024.soongan.plugin.libs
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.process.CommandLineArgumentProvider
import java.io.File

class AndroidRoomDatabaseConventionPlugin : BaseConventionPlugin({
    with(plugins) {
        apply(Plugins.KSP)
    }


    extensions.configure<KspExtension> {
        arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
    }

    dependencies {
        implementation(libs.android.room.runtime)
        implementation(libs.android.room.ktx)
        ksp(libs.android.room.compiler)
    }
})

private class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File,
) : CommandLineArgumentProvider {

    override fun asArguments() = listOf("room.schemaLocation=${schemaDir.path}")
}
