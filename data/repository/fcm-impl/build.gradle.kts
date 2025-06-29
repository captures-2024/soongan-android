import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.repository.impl)
}

android {
    namespace = "com.captures2024.soongan.data.repository.fcm.impl"
}

dependencies {
    implementation(projects.domain.repository.fcm)

    implementation(projects.data.source.fcm)
}
