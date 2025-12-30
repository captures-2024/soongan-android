plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.home)

    implementation(projects.domain.usecase.home)
    implementation(projects.domain.usecase.utils)
}
