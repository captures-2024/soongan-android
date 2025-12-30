import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.repository.impl)
}

android {
    namespace = "com.captures2024.soongan.data.repository.contest.impl"
}

dependencies {
    implementation(projects.domain.repository.contest)

    implementation(projects.data.source.contest)
    implementation(projects.data.source.member)
}
