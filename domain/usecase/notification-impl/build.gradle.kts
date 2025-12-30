plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.notification)

    implementation(projects.domain.usecase.notification)
    implementation(projects.domain.usecase.utils)
}
