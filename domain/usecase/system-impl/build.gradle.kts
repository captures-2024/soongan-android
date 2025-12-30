plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.system)

    implementation(projects.domain.usecase.system)
    implementation(projects.domain.usecase.utils)
}
