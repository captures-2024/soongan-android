import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.library.compose)
}

android {
    namespace = "com.captures2024.soongan.presentation.designsystem.ui"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.presentation.designSystem.icon)

    implementation(libs.android.core)
    implementation(libs.android.splash.screen)
}
