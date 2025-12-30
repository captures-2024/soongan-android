plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.token)

    implementation(projects.domain.usecase.token)
    implementation(projects.domain.usecase.utils)
}
