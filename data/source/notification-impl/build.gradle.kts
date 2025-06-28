import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
}

android {
    namespace = "com.captures2024.soongan.data.source.notification.impl"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(projects.data.service.api)

    implementation(projects.data.source.notification)
    implementation(projects.data.source.utils)
}
