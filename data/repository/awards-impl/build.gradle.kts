import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.repository.impl)
}

android {
    namespace = "com.captures2024.soongan.data.repository.awards.impl"
}

dependencies {
    implementation(projects.domain.repository.awards)

    implementation(projects.data.source.awards)
}
