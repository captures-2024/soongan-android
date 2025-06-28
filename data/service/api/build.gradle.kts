import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
    alias(libs.plugins.captures2024.soongan.okhttp)
    alias(libs.plugins.captures2024.soongan.retrofit)
}

android {
    namespace = "com.captures2024.soongan.data.service.api"
}

dependencies {
    implementation(projects.core.model)
}
