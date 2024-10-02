import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
    alias(libs.plugins.captures2024.soongan.retrofit)
}

android {
    namespace = "com.captures2024.soongan.core.data"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.datastore)
    implementation(projects.core.model)
}
