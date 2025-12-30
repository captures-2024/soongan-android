import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
}

android {
    namespace = "com.captures2024.soongan.data.repository.auth.impl"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(projects.domain.repository.auth)

    implementation(projects.data.source.auth)
    implementation(projects.data.source.token)
}
