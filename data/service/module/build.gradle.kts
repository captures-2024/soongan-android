plugins {
    alias(libs.plugins.captures2024.soongan.data.service)
}

android {
    namespace = "com.captures2024.soongan.data.service.module"
}

dependencies {
    implementation(projects.data.service.api)
}
