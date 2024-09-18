import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

fun PluginDependenciesSpec.captures(pluginName: String): PluginDependencySpec = id("com.captures2024.soongan.$pluginName")