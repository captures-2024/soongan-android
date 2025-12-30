plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.fcm)

    implementation(projects.domain.usecase.fcm)
    implementation(projects.domain.usecase.utils)
}
