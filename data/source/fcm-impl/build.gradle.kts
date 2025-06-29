import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.data.source.impl)
    alias(libs.plugins.captures2024.soongan.google.firebase)
}

android {
    namespace = "com.captures2024.soongan.data.source.fcm.impl"
}

dependencies {
    implementation(projects.data.source.fcm)
}
