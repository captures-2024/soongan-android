import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
}

android {
    namespace = "com.captures2024.soongan.core.domain"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(libs.javax.inject)
}
