plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.awards)

    implementation(projects.domain.usecase.awards)
    implementation(projects.domain.usecase.utils)
}
