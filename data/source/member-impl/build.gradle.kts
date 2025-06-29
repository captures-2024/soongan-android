import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.data.source.impl)
}

android {
    namespace = "com.captures2024.soongan.data.source.member.impl"
}

dependencies {
    implementation(projects.data.source.member)
}
