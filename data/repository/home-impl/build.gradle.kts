import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.repository.impl)
}

android {
    namespace = "com.captures2024.soongan.data.repository.home.impl"
}

dependencies {
    implementation(projects.domain.repository.home)

    implementation(projects.data.source.home)
    implementation(projects.data.source.member)
}
