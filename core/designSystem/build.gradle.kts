import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.library.compose)
}

android {
    namespace = "com.captures2024.soongan.core.design"
}

dependencies {
    implementation(projects.core.analytics)

    implementation(libs.android.xml.core)
    implementation(libs.android.splash.screen)
}
