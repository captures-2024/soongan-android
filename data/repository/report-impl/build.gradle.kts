import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.repository.impl)
}

android {
    namespace = "com.captures2024.soongan.data.repository.report.impl"
}

dependencies {
    implementation(projects.domain.repository.report)

    implementation(projects.data.source.report)
    implementation(projects.data.source.contest)
}
