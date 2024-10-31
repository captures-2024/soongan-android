package com.captures2024.soongan.plugin

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Action
import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.NamedDomainObjectContainerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal val Project.libs
    get() = the<LibrariesForLibs>()

internal val Project.isAndroidProject: Boolean
    get() = pluginManager.hasPlugin(Plugins.ANDROID_APPLICATION) || pluginManager.hasPlugin(Plugins.ANDROID_LIBRARY)

internal val Project.androidExtensions: CommonExtension<*, *, *, *, *, *>
    get() = when {
        pluginManager.hasPlugin(Plugins.ANDROID_APPLICATION) -> extensions.getByType<BaseAppModuleExtension>()

        pluginManager.hasPlugin(Plugins.ANDROID_LIBRARY) -> extensions.getByType<LibraryExtension>()

        else -> throw GradleException("The provided project does not have the Android plugin applied. ($name)")
    }

internal inline operator fun <T : Any, C : NamedDomainObjectContainer<T>> C.invoke(
    configuration: Action<NamedDomainObjectContainerScope<T>>,
): C = apply {
    configuration.execute(NamedDomainObjectContainerScope.of(this))
}

internal fun Project.configureAndroid(extension: CommonExtension<*, *, *, *, *, *>) {
    extension.apply {
        compileSdk = libs.versions.compileSdk.get().toInt()

        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
        }

        buildFeatures {
            buildConfig = true
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        extensions.configure<KotlinProjectExtension> {
            jvmToolchain(17)
        }

        dependencies {
            coreLibraryDesugaring(libs.android.desugar.libs)
            detektPlugins(libs.detekt.formatting)
        }

        buildTypes {

            getByName("release") {
//                isMinifyEnabled = true
//                isShrinkResources = true
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
            }
        }
    }
}

internal fun Project.configureCompose(extension: CommonExtension<*, *, *, *, *, *>) {
    extension.apply {
        with(plugins) {
            apply(Plugins.KOTLIN_COMPOSE)
        }

        buildFeatures {
            compose = true
        }

        dependencies {
            implementation(platform(libs.android.compose.bom))
            androidTestImplementation(platform(libs.android.compose.bom))
            implementation(libs.bundles.compose)
        }
    }

    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.add("-P")
            freeCompilerArgs.add("plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$rootDir/report/compose-metrics")
            freeCompilerArgs.add("-P")
            freeCompilerArgs.add("plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$rootDir/report/compose-reports")

        }
    }
}
