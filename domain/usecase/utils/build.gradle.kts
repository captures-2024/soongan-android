import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
}

android {
    namespace = "com.captures2024.soongan.domain.usecase.utils"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.javax.inject)
}
