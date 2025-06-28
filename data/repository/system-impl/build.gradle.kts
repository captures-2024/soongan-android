import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.repository.impl)
}

android {
    namespace = "com.captures2024.soongan.data.repository.system.impl"
}

dependencies {
    implementation(projects.domain.repository.system)

    implementation(projects.data.source.system)
}
