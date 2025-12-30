plugins {
    alias(libs.plugins.captures2024.soongan.usecase.impl)
}

dependencies {
    implementation(projects.domain.repository.member)
    implementation(projects.domain.repository.report)

    implementation(projects.domain.usecase.report)
    implementation(projects.domain.usecase.utils)
}
