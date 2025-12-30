plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.auth)

    implementation(projects.domain.usecase.auth)
    implementation(projects.domain.usecase.utils)
}
