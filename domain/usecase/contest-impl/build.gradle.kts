plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.contest)
    implementation(projects.domain.repository.member)

    implementation(projects.domain.usecase.contest)
    implementation(projects.domain.usecase.utils)
}
