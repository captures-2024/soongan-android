import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
}

android {
    namespace = "com.captures2024.soongan.data.source.auth"
}

dependencies {
    implementation(projects.core.model)
}
