import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.library.compose)
    alias(libs.plugins.captures2024.soongan.android.hilt)
}

android {
    namespace = "com.captures2024.soongan.core.analytics.android"
}

dependencies {
    implementation(projects.core.analytics)

    implementation(libs.android.compose.runtime)
    implementation(libs.aakira.napier)
}
